package com.adityap.nyt.data.story;

import com.adityap.nyt.data.story.cache.StoryCache;
import com.adityap.nyt.data.story.cloud.SectionMapper;
import com.adityap.nyt.data.story.cloud.StoryApi;
import com.adityap.nyt.domain.core.Storage;
import com.adityap.nyt.domain.core.StorageKeys;
import com.adityap.nyt.domain.model.story.Story;
import com.adityap.nyt.domain.model.story.StoryService;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class StoryServiceImpl implements StoryService
{
    private StoryApi api;
    private StoryCache cache;
    private Storage storage;

    public StoryServiceImpl(StoryApi api, StoryCache cache, Storage storage)
    {
        Timber.v("StoryService init");
        this.api = api;
        this.cache = cache;
        this.storage = storage;
    }

    @Override
    public Observable<List<Story>> getStories(int section)
    {
        return cache.getStories(section)
                    .flatMap(cachedStories -> {
                        if (cachedStories.isEmpty())
                        {
                            return refreshStories(section);
                        }
                        else
                        {
                            return Observable.just(cachedStories);
                        }
                    });
    }

    @Override
    public Observable<List<Story>> refreshStories(int section)
    {
        String sectionName = SectionMapper.getString(section);
        return api.getStories(sectionName)
                  .map(response -> {
                      storage.put(StorageKeys.COPYRIGHT_MESSAGE, response
                              .getCopyrightMessage());
                      return response.getStories();
                  })
                  .doOnNext(stories1 -> cache.put(section, stories1))
                  .subscribeOn(Schedulers.newThread());
    }
}
