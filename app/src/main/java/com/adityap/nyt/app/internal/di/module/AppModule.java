package com.adityap.nyt.app.internal.di.module;

import android.content.Context;

import com.adityap.nyt.app.App;
import com.adityap.nyt.app.internal.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    private App app;

    public AppModule(App app)
    {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    public Context provideApplicationContext()
    {
        return app;
    }

    @Provides
    @ApplicationScope
    public App provideApplication()
    {
        return app;
    }
}
