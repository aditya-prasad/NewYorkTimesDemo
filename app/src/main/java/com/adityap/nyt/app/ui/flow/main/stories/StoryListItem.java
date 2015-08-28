package com.adityap.nyt.app.ui.flow.main.stories;

import com.adityap.nyt.app.mvp.stories.StoryModel;
import com.adityap.nyt.domain.model.story.Story;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StoryListItem implements StoryModel
{
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM");

    private String title;
    private String author;
    private String imageUrl;
    private String publishDate;

    public StoryListItem(Story story)
    {
        title = story.getTitle();
        author = story.getAuthor();
        imageUrl = story.getThumbnailUrl();
        publishDate = story.getPublishTime().toString(formatter);
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public String getPublishDate()
    {
        return publishDate;
    }
}
