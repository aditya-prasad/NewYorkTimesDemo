package com.adityap.nyt.data.story.cloud;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class NytStoryDeserializer implements JsonDeserializer<StoryApiResponse.Story>
{
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat
            .forPattern("yyyy-MM-dd'T'HH:mm:ssZZ");
    ;

    @Override
    public StoryApiResponse.Story deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        try
        {
            StoryApiResponse.Story story = new StoryApiResponse.Story();
            JsonObject jsonObject = json.getAsJsonObject();

            story.title = jsonObject.get("title").getAsString();
            story.section = jsonObject.get("section").getAsString();
            story.summary = jsonObject.get("abstract").getAsString();
            story.url = jsonObject.get("url").getAsString();

            String byline = jsonObject.get("byline").getAsString();
            story.author = byline.substring(3);

            String publishTimeStr = jsonObject.get("published_date").getAsString();
            if (publishTimeStr.lastIndexOf('-') == 19 || publishTimeStr.lastIndexOf('+') == 19)
            {
                StringBuilder stringBuilder = new StringBuilder(publishTimeStr.substring(0, 20));
                stringBuilder.append(0);
                stringBuilder.append(publishTimeStr.substring(20));
                publishTimeStr = stringBuilder.toString();
            }
            story.publishTime = DateTime.parse(publishTimeStr, dateTimeFormatter);

            JsonElement multimedia = jsonObject.get("multimedia");
            if (multimedia.isJsonArray())
            {
                Type type = new TypeToken<List<StoryApiResponse.Story.Image>>()
                {
                }.getType();
                story.images = context.deserialize(multimedia, type);
            }
            else
            {
                story.images = new ArrayList<>();
            }
            return story;
        }
        catch (Exception e)
        {
            Timber.e("Error while deserializing StoryAPiResponse.Story [" + e.getMessage() + "]");
        }
        return null;
    }
}
