package com.adityap.nyt.app.mvp.details;

import com.adityap.nyt.app.mvp.core.StateCache;
import com.adityap.nyt.domain.model.story.Story;
import com.google.gson.Gson;

import java.util.List;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class StoryDetailsPresenterImpl implements StoryDetailsPresenter
{
    /* View */
    private StoryDetailsView view;

    /* State */
    private StateCache stateCache;
    private StoryDetailsState state;

    /* Model Mapper */
    private StoryDetailsModelMapper mapper;

    /* Subscriptions */
    private CompositeSubscription subscriptions;

    public StoryDetailsPresenterImpl(StateCache stateCache, List<Story> stories, int initialPosition, StoryDetailsModelMapper mapper)
    {
        this.stateCache = stateCache;
        this.mapper = mapper;
        this.subscriptions = new CompositeSubscription();

        state = stateCache.get(StoryDetailsPresenter.class, StoryDetailsState.class);
        if(state == null)
        {
            this.state = StoryDetailsState.storyDetails(stories, initialPosition);
        }
    }

    @Override
    public void setActivePosition(int position)
    {
        state.newPosition(position);
    }

    @Override
    public void attachView(StoryDetailsView view)
    {
        this.view = view;

        if (state != null)
        {
            state.render(view, mapper);
        }
    }

    @Override
    public void detachView()
    {
        view = null;

        if (state != null)
        {
            stateCache.put(StoryDetailsPresenter.class, state);
        }
    }

    @Override
    public boolean isViewAttached()
    {
        return (view != null);
    }

    @Override
    public void destroy()
    {
        detachView();
        state = null;
        stateCache.remove(StoryDetailsPresenter.class);
    }
}
