package com.adityap.nyt.app.mvp.core;

public interface Presenter<V extends View>
{
    void attachView(V view);

    void detachView();

    boolean isViewAttached();

    void destroy();
}
