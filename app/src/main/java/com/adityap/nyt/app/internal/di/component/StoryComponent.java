package com.adityap.nyt.app.internal.di.component;

import com.adityap.nyt.app.internal.di.module.StoryModule;
import com.adityap.nyt.app.internal.di.scope.StoryScope;
import com.adityap.nyt.domain.model.story.Story;

import dagger.Component;

@StoryScope
@Component(modules = StoryModule.class, dependencies = AppComponent.class)
public interface StoryComponent
{
    Story activeStory();
}
