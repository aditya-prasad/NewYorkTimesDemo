package com.adityap.nyt.domain.model.story;

import java.util.List;

import rx.Observable;

public interface StoryService
{
    Observable<List<Story>> getStories(int section);

    Observable<List<Story>> refreshStories(int section);
}
