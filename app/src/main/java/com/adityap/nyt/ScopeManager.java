package com.adityap.nyt;

import com.adityap.nyt.internal.di.component.AppComponent;

public class ScopeManager
{
    private AppComponent appComponent;

    private static ScopeManager instance;

    private ScopeManager(AppComponent appComponent)
    {
        this.appComponent = appComponent;
    }

    public static ScopeManager getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException("ScopeManager not initialized");
        }
        return instance;
    }

    public static void initialize(AppComponent appComponent)
    {
        instance = new ScopeManager(appComponent);
    }

    public AppComponent appComponent()
    {
        return appComponent;
    }
}
