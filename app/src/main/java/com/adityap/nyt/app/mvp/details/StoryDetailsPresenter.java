package com.adityap.nyt.app.mvp.details;

import com.adityap.nyt.app.mvp.core.Presenter;

public interface StoryDetailsPresenter extends Presenter<StoryDetailsView>
{
    void setActivePosition(int position);
}
