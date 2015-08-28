package com.adityap.nyt.domain.usecase;

import com.adityap.nyt.domain.core.UseCase;
import com.adityap.nyt.domain.model.story.Story;
import com.adityap.nyt.domain.model.story.StoryService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RefreshStoriesUseCase implements UseCase<List<Story>>
{
    private StoryService storyService;
    private int section;

    @Inject
    public RefreshStoriesUseCase(StoryService storyService)
    {
        this.storyService = storyService;
        System.out.println("[DOMAIN LAYER] RefreshStoriesUseCase created");
    }

    public void setSection(int section)
    {
        this.section = section;
    }

    @Override
    public Observable<List<Story>> execute()
    {
        return storyService.refreshStories(section);
    }
}
