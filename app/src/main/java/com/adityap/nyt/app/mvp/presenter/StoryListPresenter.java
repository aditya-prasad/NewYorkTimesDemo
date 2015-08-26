package com.adityap.nyt.app.mvp.presenter;

import com.adityap.nyt.app.mvp.core.Presenter;
import com.adityap.nyt.app.mvp.model.StoryModel;
import com.adityap.nyt.app.mvp.view.StoryListView;

public interface StoryListPresenter extends Presenter<StoryListView>
{
    void changeSection(String section);

    void selectStory(StoryModel story);
}
