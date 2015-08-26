package com.adityap.nyt.internal.di.component;

import android.content.Context;

import com.adityap.nyt.App;
import com.adityap.nyt.internal.di.module.AppModule;
import com.adityap.nyt.internal.di.scope.ApplicationScope;

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
