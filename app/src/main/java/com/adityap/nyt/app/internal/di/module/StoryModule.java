package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.qualifier.StoryDetailsInitialPosition;
import com.adityap.nyt.app.internal.di.scope.StoryScope;
import com.adityap.nyt.domain.model.story.Story;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class StoryModule
{
    private List<Story> stories;
    private int initialPosition;

    public StoryModule(List<Story> activeStory, int initialPosition)
    {
        this.stories = activeStory;
        this.initialPosition = initialPosition;
    }

    @Provides
    @StoryScope
    public List<Story> provideStories()
    {
        return stories;
    }

    @Provides
    @StoryScope
    @StoryDetailsInitialPosition
    public int provideInitialPosition()
    {
        return initialPosition;
    }
}
