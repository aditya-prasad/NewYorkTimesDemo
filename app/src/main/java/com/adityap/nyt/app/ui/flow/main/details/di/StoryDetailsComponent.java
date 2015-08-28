package com.adityap.nyt.app.ui.flow.main.details.di;

import com.adityap.nyt.app.internal.di.scope.ScreenScope;
import com.adityap.nyt.app.mvp.details.StoryDetailsPresenter;
import com.adityap.nyt.app.ui.flow.main.details.StoryDetailsContainerFragment;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = StoryDetailsModule.class)
public interface StoryDetailsComponent
{
    StoryDetailsPresenter presenter();

    void injectIn(StoryDetailsContainerFragment fragment);
}
