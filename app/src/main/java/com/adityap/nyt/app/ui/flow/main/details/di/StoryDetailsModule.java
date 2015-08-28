package com.adityap.nyt.app.ui.flow.main.details.di;

import com.adityap.nyt.app.internal.di.qualifier.StoryDetailsInitialPosition;
import com.adityap.nyt.app.internal.di.scope.ScreenScope;
import com.adityap.nyt.app.mvp.core.StateCache;
import com.adityap.nyt.app.mvp.details.StoryDetailsModelMapper;
import com.adityap.nyt.app.mvp.details.StoryDetailsPresenter;
import com.adityap.nyt.app.mvp.details.StoryDetailsPresenterImpl;
import com.adityap.nyt.app.ui.flow.main.details.StoryDetailsModelMapperImpl;
import com.adityap.nyt.domain.model.story.Story;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class StoryDetailsModule
{
    @Provides
    @ScreenScope
    public StoryDetailsModelMapper provideStoryDetailsModelMapper()
    {
        return new StoryDetailsModelMapperImpl();
    }

    @Provides
    @ScreenScope
    public StoryDetailsPresenter provideStoryDetailsPresenter(StateCache stateCache, List<Story> stories, @StoryDetailsInitialPosition int initialPosition, StoryDetailsModelMapper mapper)
    {
        return new StoryDetailsPresenterImpl(stateCache, stories, initialPosition, mapper);
    }
}
