package com.adityap.nyt.data.story;

import com.adityap.nyt.data.story.cache.StoryCache;
import com.adityap.nyt.data.story.cloud.StoryApi;

import javax.inject.Inject;

public class StoryService
{
    private StoryApi api;
    private StoryCache cache;

    @Inject
    public StoryService(StoryApi api, StoryCache cache)
    {
        this.api = api;
        this.cache = cache;
    }
}
