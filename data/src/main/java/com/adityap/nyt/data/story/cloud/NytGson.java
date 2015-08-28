package com.adityap.nyt.data.story.cloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NytGson
{
    public static Gson get()
    {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(StoryApiResponse.Story.class, new NytStoryDeserializer());
        return gsonBuilder.create();
    }
}
