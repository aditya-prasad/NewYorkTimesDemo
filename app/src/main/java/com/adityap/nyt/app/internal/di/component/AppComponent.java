package com.adityap.nyt.app.internal.di.component;

import android.content.Context;

import com.adityap.nyt.app.App;
import com.adityap.nyt.app.internal.di.module.AppModule;
import com.adityap.nyt.app.internal.di.module.DomainModule;
import com.adityap.nyt.app.internal.di.module.MvpModule;
import com.adityap.nyt.app.internal.di.module.StoryModule;
import com.adityap.nyt.app.internal.di.module.UtilModule;
import com.adityap.nyt.app.internal.di.qualifier.PrettyGson;
import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.adityap.nyt.app.ui.flow.main.stories.di.StoryListComponent;
import com.adityap.nyt.app.ui.flow.main.stories.di.StoryListModule;
import com.google.gson.Gson;

import dagger.Component;

@ApplicationScope
@Component(
        modules = {
                AppModule.class,
                UtilModule.class,
                MvpModule.class,
                DomainModule.class
        })
public interface AppComponent
{
    Context context();

    App application();

    Gson gson();

    @PrettyGson
    Gson prettyGson();

    StoryComponent plus(StoryModule module);

    StoryListComponent plus(StoryListModule module);
}
