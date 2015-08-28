package com.adityap.nyt.app.ui.flow.main.details;

import android.text.Html;

import com.adityap.nyt.app.mvp.details.StoryDetailsModel;
import com.adityap.nyt.domain.model.story.Story;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StoryDetails implements StoryDetailsModel
{
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM");

    private String title;
    private String author;
    private String date;
    private String imageUrl;
    private String imageCaption;
    private String summary;
    private String url;

    public StoryDetails(Story story)
    {
        title = story.getTitle();
        author = story.getAuthor();
        date = story.getPublishTime().toString(formatter);
        imageUrl = story.getImageUrl();
        imageCaption = story.getImageCaption();
        summary = story.getSummary();
        url = story.getUrl();
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getDate()
    {
        return date;
    }

    public boolean isImageAvailable()
    {
        return (imageUrl != null);
    }

    public boolean isImageCaptionAvailable()
    {
        return (imageCaption != null);
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public String getImageCaption()
    {
        return imageCaption;
    }

    public String getSummary()
    {
        return summary;
    }

    public String getUrl()
    {
        return url;
    }
}
