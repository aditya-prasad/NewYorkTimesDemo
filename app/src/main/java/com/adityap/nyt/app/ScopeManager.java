package com.adityap.nyt.app;

import com.adityap.nyt.app.internal.di.component.AppComponent;
import com.adityap.nyt.app.internal.di.component.StoryComponent;
import com.adityap.nyt.app.internal.di.module.StoryModule;
import com.adityap.nyt.domain.model.story.Story;

import java.util.List;

import timber.log.Timber;

public class ScopeManager
{
    private AppComponent appComponent;
    private StoryComponent storyComponent;

    private static ScopeManager instance;

    private ScopeManager(AppComponent appComponent)
    {
        this.appComponent = appComponent;
    }

    public static ScopeManager getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException("ScopeManager not initialized");
        }
        return instance;
    }

    public static void initialize(AppComponent appComponent)
    {
        instance = new ScopeManager(appComponent);
        Timber.v("ScopeManager initialized");
    }

    public void startStoryScope(List<Story> stories, int initialPosition)
    {
        storyComponent = appComponent.plus(new StoryModule(stories, initialPosition));

        Timber.v("StoryScope started");
    }

    public void endStoryScope()
    {
        storyComponent = null;

        Timber.v("StoryScope ended");
    }

    public AppComponent appComponent()
    {
        return appComponent;
    }

    public StoryComponent storyComponent()
    {
        return storyComponent;
    }
}
