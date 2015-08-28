package com.adityap.nyt.app.ui.flow.main.stories.di;

import com.adityap.nyt.app.internal.di.scope.ScreenScope;
import com.adityap.nyt.app.mvp.core.StateCache;
import com.adityap.nyt.app.mvp.stories.StoryListPresenter;
import com.adityap.nyt.app.mvp.stories.StoryListPresenterImpl;
import com.adityap.nyt.app.mvp.stories.StoryModelMapper;
import com.adityap.nyt.app.ui.flow.main.stories.StoryModelMapperImpl;
import com.adityap.nyt.domain.usecase.FetchStoriesUseCase;
import com.adityap.nyt.domain.usecase.RefreshStoriesUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class StoryListModule
{
    @Provides
    @ScreenScope
    public StoryModelMapper provideStoryModelMapper()
    {
        return new StoryModelMapperImpl();
    }

    @Provides
    @ScreenScope
    public StoryListPresenter providePresenter(StateCache stateCache, StoryModelMapper mapper, FetchStoriesUseCase fetchStoriesUseCase, RefreshStoriesUseCase refreshStoriesUseCase)
    {
        return new StoryListPresenterImpl(stateCache, mapper, fetchStoriesUseCase, refreshStoriesUseCase);
    }
}
