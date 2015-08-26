package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.scope.StoryScope;
import com.adityap.nyt.domain.model.story.Story;

import dagger.Module;
import dagger.Provides;

@Module
public class StoryModule
{
    private Story activeStory;

    public StoryModule(Story activeStory)
    {
        this.activeStory = activeStory;
    }

    @Provides
    @StoryScope
    public Story provideActiveStory()
    {
        return activeStory;
    }
}
