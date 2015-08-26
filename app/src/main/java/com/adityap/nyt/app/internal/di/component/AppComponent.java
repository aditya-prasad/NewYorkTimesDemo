package com.adityap.nyt.app.internal.di.component;

import android.content.Context;

import com.adityap.nyt.app.App;
import com.adityap.nyt.app.internal.di.module.ApiModule;
import com.adityap.nyt.app.internal.di.module.AppModule;
import com.adityap.nyt.app.internal.di.module.CacheModule;
import com.adityap.nyt.app.internal.di.module.DatabaseModule;
import com.adityap.nyt.app.internal.di.module.DomainModule;
import com.adityap.nyt.app.internal.di.module.NetworkModule;
import com.adityap.nyt.app.internal.di.module.UtilModule;
import com.adityap.nyt.app.internal.di.qualifier.PrettyGson;
import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.adityap.nyt.data.internal.sqlite.SQLiteManager;
import com.google.gson.Gson;

import dagger.Component;

@ApplicationScope
@Component(
        modules = {
                AppModule.class,
                UtilModule.class,
                DomainModule.class
        })
public interface AppComponent
{
    Context context();

    App application();

    Gson gson();

    @PrettyGson
    Gson prettyGson();
}
