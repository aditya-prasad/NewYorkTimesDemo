package com.adityap.nyt.data.impl;

import com.adityap.nyt.data.story.cache.StoryCache;
import com.adityap.nyt.domain.model.story.Story;

import java.util.List;

import rx.Observable;

public class SQLiteStoryCache implements StoryCache
{
    @Override
    public Observable<List<Story>> getStories(int section)
    {
        return null;
    }

    @Override
    public void put(int section, List<Story> stories)
    {

    }
}
