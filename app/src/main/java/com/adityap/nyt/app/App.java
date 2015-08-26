package com.adityap.nyt.app;

import android.app.Application;

import com.adityap.nyt.BuildConfig;
import com.adityap.nyt.app.internal.di.component.AppComponent;
import com.adityap.nyt.app.internal.di.component.DaggerAppComponent;
import com.adityap.nyt.app.internal.di.module.AppModule;

import timber.log.Timber;

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        initLogging();
        Timber.i("Application Started");
    }

    private void initLogging()
    {
        if(BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initDagger()
    {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

        ScopeManager.initialize(appComponent);
    }
}
