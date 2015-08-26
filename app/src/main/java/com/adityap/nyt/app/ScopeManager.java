package com.adityap.nyt.app;

import com.adityap.nyt.app.internal.di.component.AppComponent;
import com.adityap.nyt.app.internal.di.component.DaggerStoryComponent;
import com.adityap.nyt.app.internal.di.component.StoryComponent;
import com.adityap.nyt.app.internal.di.module.StoryModule;
import com.adityap.nyt.domain.model.story.Story;

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
    }

    public void startStoryScope(Story story)
    {
        storyComponent = DaggerStoryComponent.builder()
                .appComponent(appComponent)
                .storyModule(new StoryModule(story))
                .build();
    }

    public void endStoryScope()
    {
        storyComponent = null;
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
