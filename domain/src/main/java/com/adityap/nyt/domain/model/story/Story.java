package com.adityap.nyt.domain.model.story;

import org.joda.time.DateTime;

public class Story
{
    private int section;
    private String title;
    private String summary;
    private String url;
    private DateTime publishTime;
    private String thumbnailUrl;
    private String imageUrl;
    private String imageCaption;

    public int getSection()
    {
        return section;
    }

    public void setSection(int section)
    {
        this.section = section;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public DateTime getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime(DateTime publishTime)
    {
        this.publishTime = publishTime;
    }

    public String getThumbnailUrl()
    {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl)
    {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageCaption()
    {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption)
    {
        this.imageCaption = imageCaption;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Story))
        {
            return false;
        }

        Story story = (Story) o;

        if (title.equals(story.title) && publishTime.equals(story.publishTime))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        int result = title.hashCode();
        result = 31 * result + publishTime.hashCode();
        return result;
    }
}
