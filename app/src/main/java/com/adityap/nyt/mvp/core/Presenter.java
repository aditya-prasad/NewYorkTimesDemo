package com.adityap.nyt.mvp.core;

public interface Presenter<V extends View>
{
    void attachView(V view);

    void detachView();

    boolean isViewAttached();
}
