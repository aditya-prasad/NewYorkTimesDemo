package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.adityap.nyt.data.story.cloud.StoryApi;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(includes = NetworkModule.class)
public class ApiModule
{
    @Provides
    @ApplicationScope
    public StoryApi provideStoryApi(RestAdapter restAdapter)
    {
        return restAdapter.create(StoryApi.class);
    }
}
