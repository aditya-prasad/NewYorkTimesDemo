package com.adityap.nyt.app.mvp.stories;

import com.adityap.nyt.app.ScopeManager;
import com.adityap.nyt.app.mvp.core.StateCache;
import com.adityap.nyt.app.mvp.details.StoryDetailsPresenter;
import com.adityap.nyt.domain.model.story.Section;
import com.adityap.nyt.domain.model.story.Story;
import com.adityap.nyt.domain.usecase.FetchStoriesUseCase;
import com.adityap.nyt.domain.usecase.RefreshStoriesUseCase;
import com.google.gson.Gson;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class StoryListPresenterImpl implements StoryListPresenter
{
    /* View */
    private StoryListView view;

    /* State */
    private StateCache stateCache;
    private StoryListState state;

    /* Model Mapper */
    private StoryModelMapper mapper;

    /* Use Cases */
    private FetchStoriesUseCase fetchStoriesUseCase;
    private RefreshStoriesUseCase refreshStoriesUseCase;

    /* Subscriptions */
    private CompositeSubscription subscriptions;

    public StoryListPresenterImpl(StateCache stateCache, StoryModelMapper mapper, FetchStoriesUseCase fetchStoriesUseCase, RefreshStoriesUseCase refreshStoriesUseCase)
    {
        Timber.v("StoryListPresenter Initialized");
        this.stateCache = stateCache;
        this.mapper = mapper;
        this.fetchStoriesUseCase = fetchStoriesUseCase;
        this.refreshStoriesUseCase = refreshStoriesUseCase;
        this.subscriptions = new CompositeSubscription();

        this.stateCache.remove(StoryDetailsPresenter.class);
    }

    @Override
    public void changeSection(final int section)
    {
        state = StoryListState.loading(section);
        view.showLoading();

        final long startTime = System.currentTimeMillis();

        fetchStoriesUseCase.setSection(section);
        Subscription fetchStoriesSubscription =
                fetchStoriesUseCase
                        .execute()
                        .doOnNext(stories -> state = StoryListState.stories(section, stories))
                        .map(mapper::map)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<StoryModel>>()
                        {
                            @Override
                            public void onCompleted()
                            {
                                long endTime = System.currentTimeMillis();
                                Timber.i("Time (Fetch stories for section = " + section + ") = " + (endTime - startTime) + "ms");
                            }

                            @Override
                            public void onError(Throwable e)
                            {
                                Timber.e("Error while fetching event list for section = " + section);
                                e.printStackTrace();
                                state = StoryListState.error(section);

                                view.displayError();
                            }

                            @Override
                            public void onNext(List<StoryModel> stories)
                            {
                                if (stories.isEmpty())
                                {
                                    state = StoryListState.noStories(section);
                                    view.displayNoStoriesMessage();
                                }
                                else
                                {
                                    view.displayStories(stories);
                                }
                            }
                        });

        subscriptions.add(fetchStoriesSubscription);
    }

    @Override
    public void refreshSection(int section)
    {
        final long startTime = System.currentTimeMillis();

        refreshStoriesUseCase.setSection(section);
        Subscription refreshStoriesSubscription =
                refreshStoriesUseCase
                        .execute()
                        .doOnNext(stories -> state = StoryListState.stories(section, stories))
                        .map(mapper::map)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<StoryModel>>()
                        {
                            @Override
                            public void onCompleted()
                            {
                                long endTime = System.currentTimeMillis();
                                Timber.i("Time (Refresh stories for section = " + section + ") = " + (endTime - startTime) + "ms");
                            }

                            @Override
                            public void onError(Throwable e)
                            {
                                Timber.e("Error while refreshing event list for section = " + section);
                                e.printStackTrace();
                                state = StoryListState.error(section);

                                view.displayError();
                            }

                            @Override
                            public void onNext(List<StoryModel> stories)
                            {
                                if (stories.isEmpty())
                                {
                                    state = StoryListState.noStories(section);
                                    view.displayNoStoriesMessage();
                                }
                                else
                                {
                                    view.displayStories(stories);
                                }
                            }
                        });

        subscriptions.add(refreshStoriesSubscription);
    }

    @Override
    public void selectStory(String title, String author)
    {
        Story story = new Story();
        story.setTitle(title);
        story.setAuthor(author);

        List<Story> stories = state.getStories();
        int initialPosition = stories.indexOf(story);
        ScopeManager.getInstance().startStoryScope(state.getStories(), initialPosition);

        view.navigateToDetailsView();
    }

    @Override
    public void attachView(StoryListView view)
    {
        // End the details view scope
        ScopeManager.getInstance().endStoryScope();

        this.view = view;

        state = stateCache.get(StoryListPresenter.class, StoryListState.class);
        if (state != null)
        {
            if (state.isLoading())
            {
                changeSection(state.getSection());

            }
            state.render(this.view, mapper);
        }
        else
        {
            view.selectSection(Section.WORLD);
        }
    }

    @Override
    public void detachView()
    {
        view = null;

        if (subscriptions.hasSubscriptions())
        {
            subscriptions.unsubscribe();
        }

        if (state != null)
        {
            stateCache.put(StoryListPresenter.class, state);
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
        stateCache.remove(StoryListPresenter.class);
    }
}
