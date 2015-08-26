package com.adityap.nyt.app.internal.di.component;

import android.content.Context;

import com.adityap.nyt.app.App;
import com.adityap.nyt.app.internal.di.module.AppModule;
import com.adityap.nyt.app.internal.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {
        AppModule.class
})
public interface AppComponent
{
    Context context();

    App application();
}
