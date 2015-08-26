package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.adityap.nyt.data.story.StoryServiceImpl;
import com.adityap.nyt.data.story.cache.StoryCache;
import com.adityap.nyt.data.story.cloud.StoryApi;
import com.adityap.nyt.domain.core.Storage;
import com.adityap.nyt.domain.model.story.StoryService;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                ApiModule.class,
                CacheModule.class
        })
public class DomainModule
{
    @Provides
    @ApplicationScope
    public StoryService provideStoryService(StoryApi api, StoryCache cache, Storage storage)
    {
        return new StoryServiceImpl(api, cache, storage);
    }
}
