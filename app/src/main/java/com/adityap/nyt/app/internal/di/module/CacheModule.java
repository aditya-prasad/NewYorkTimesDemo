package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.adityap.nyt.data.impl.SQLiteStorage;
import com.adityap.nyt.data.impl.SQLiteStoryCache;
import com.adityap.nyt.data.internal.sqlite.SQLiteManager;
import com.adityap.nyt.data.story.cache.StoryCache;
import com.adityap.nyt.domain.core.Storage;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module(includes = DatabaseModule.class)
public class CacheModule
{
    @Provides
    @ApplicationScope
    public Storage provideStorage(SQLiteManager databaseManager, Gson gson)
    {
        return new SQLiteStorage(databaseManager, gson);
    }

    @Provides
    @ApplicationScope
    public StoryCache provideStoryCache()
    {
        return new SQLiteStoryCache();
    }
}
