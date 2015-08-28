package com.adityap.nyt.app.ui.flow.main.stories.di;

import com.adityap.nyt.app.internal.di.scope.ScreenScope;
import com.adityap.nyt.app.mvp.stories.StoryListPresenter;
import com.adityap.nyt.app.ui.flow.main.stories.StoryListFragment;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = StoryListModule.class)
public interface StoryListComponent
{
    StoryListPresenter presenter();

    void injectIn(StoryListFragment fragment);
}
