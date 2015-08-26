package com.adityap.nyt.data.story.cache;

import com.adityap.nyt.domain.model.story.Story;

import java.util.List;

import rx.Observable;

public interface StoryCache
{
    Observable<List<Story>> getStories(int section);

    void put(int section, List<Story> stories);
}
