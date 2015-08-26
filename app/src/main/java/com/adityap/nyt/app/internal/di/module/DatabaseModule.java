package com.adityap.nyt.app.internal.di.module;

import android.content.Context;

import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.adityap.nyt.data.internal.sqlite.SQLiteManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule
{
    @Provides
    @ApplicationScope
    public SQLiteManager provideSqliteManager(Context context)
    {
        return new SQLiteManager(context);
    }
}
