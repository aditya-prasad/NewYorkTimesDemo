package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.adityap.nyt.app.mvp.core.StateCache;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpModule
{
    @Provides
    @ApplicationScope
    public StateCache provideStateCache()
    {
        return new StateCache();
    }
}
