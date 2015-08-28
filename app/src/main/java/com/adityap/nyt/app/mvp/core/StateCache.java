package com.adityap.nyt.app.mvp.core;

import java.util.HashMap;
import java.util.Map;

public class StateCache
{
    private Map<Class<? extends Presenter>, State> memoryCache;

    public StateCache()
    {
        memoryCache = new HashMap<>();
    }

    public void put(Class<? extends Presenter> presenterClass, State state)
    {
        memoryCache.put(presenterClass, state);
    }

    public <T extends State> T get(Class<? extends Presenter> presenterClass, Class<T> stateClass)
    {
        try
        {
            return stateClass.cast(memoryCache.get(presenterClass));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void remove(Class<? extends Presenter> presenterClass)
    {
        memoryCache.remove(presenterClass);
    }
}
