package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.qualifier.PrettyGson;
import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
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
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    @PrettyGson
    public Gson providePrettyGson()
    {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setPrettyPrinting();
        return gsonBuilder.create();
    }
}
