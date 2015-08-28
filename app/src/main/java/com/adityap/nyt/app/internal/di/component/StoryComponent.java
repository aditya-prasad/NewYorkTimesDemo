package com.adityap.nyt.app.internal.di.component;

import com.adityap.nyt.app.internal.di.module.StoryModule;
import com.adityap.nyt.app.internal.di.scope.StoryScope;
import com.adityap.nyt.app.ui.flow.main.details.di.StoryDetailsComponent;
import com.adityap.nyt.app.ui.flow.main.details.di.StoryDetailsModule;

import dagger.Subcomponent;

@StoryScope
@Subcomponent(modules = StoryModule.class)
public interface StoryComponent
{
    StoryDetailsComponent plus(StoryDetailsModule module);
}
