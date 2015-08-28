package com.adityap.nyt.domain.usecase;

import com.adityap.nyt.domain.core.UseCase;
import com.adityap.nyt.domain.model.story.Story;
import com.adityap.nyt.domain.model.story.StoryService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class FetchStoriesUseCase implements UseCase<List<Story>>
{
    private StoryService storyService;
    private int section;

    @Inject
    public FetchStoriesUseCase(StoryService storyService)
    {
        this.storyService = storyService;
        System.out.println("[DOMAIN LAYER] FetchStoriesUseCase created");
    }

    public void setSection(int section)
    {
        this.section = section;
    }

    @Override
    public Observable<List<Story>> execute()
    {
        return storyService.getStories(section);
    }
}
