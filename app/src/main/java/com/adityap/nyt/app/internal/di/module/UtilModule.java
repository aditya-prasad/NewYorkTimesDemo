package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.qualifier.NytGson;
import com.adityap.nyt.app.internal.di.qualifier.PrettyGson;
import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule
{
    @Provides
    @ApplicationScope
    public Gson provideGson()
    {
        GsonBuilder gsonBuilder = Converters.registerAll(new GsonBuilder());
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    @PrettyGson
    public Gson providePrettyGson()
    {
        GsonBuilder gsonBuilder = Converters.registerAll(new GsonBuilder())
                                            .setPrettyPrinting();
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    @NytGson
    public Gson provideNytGson()
    {
        return com.adityap.nyt.data.story.cloud.NytGson.get();
    }
}
