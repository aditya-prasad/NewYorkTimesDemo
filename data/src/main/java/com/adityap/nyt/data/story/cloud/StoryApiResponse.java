package com.adityap.nyt.data.story.cloud;

import com.adityap.nyt.domain.model.story.Section;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class StoryApiResponse
{
    @SerializedName("copyright")
    private String copyrightMessage;

    @SerializedName("results")
    private List<Story> stories;

    public String getCopyrightMessage()
    {
        return copyrightMessage;
    }

    public List<com.adityap.nyt.domain.model.story.Story> getStories()
    {
        List<com.adityap.nyt.domain.model.story.Story> models = new ArrayList<>(stories.size());
        for (Story story : stories)
        {
            try
            {
                com.adityap.nyt.domain.model.story.Story model = story.getModel();
                models.add(model);
            }
            catch (Exception e)
            {
                Timber.e("Unable to convert story from api response to story model [" + e
                        .getMessage() + "]");
            }
        }
        return models;
    }

    private static class Story
    {
        @SerializedName("section")
        String section;

        @SerializedName("title")
        String title;

        @SerializedName("abstract")
        String summary;

        @SerializedName("url")
        String url;

        @SerializedName("published_date")
        DateTime publishTime;

        @SerializedName("multimedia")
        List<Image> images;

        com.adityap.nyt.domain.model.story.Story getModel()
        {
            com.adityap.nyt.domain.model.story.Story model = new com.adityap.nyt.domain.model.story.Story();
            model.setSection(SectionMapper.getCode(section));
            model.setTitle(title);
            model.setSummary(summary);
            model.setUrl(url);
            model.setPublishTime(publishTime);

            for (Story.Image image : images)
            {
                if (image.format.equalsIgnoreCase("Standard Thumbnail"))
                {
                    model.setThumbnailUrl(image.url);
                }
                else if (image.format.equalsIgnoreCase("Normal"))
                {
                    model.setImageUrl(image.url);
                    model.setImageCaption(image.caption);
                }
            }
            return model;
        }

        static class Image
        {
            @SerializedName("url")
            private String url;

            @SerializedName("format")
            private String format;

            @SerializedName("caption")
            private String caption;
        }
    }
}
