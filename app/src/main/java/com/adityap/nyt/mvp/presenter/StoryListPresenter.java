package com.adityap.nyt.mvp.presenter;

import com.adityap.nyt.mvp.core.Presenter;
import com.adityap.nyt.mvp.model.StoryModel;
import com.adityap.nyt.mvp.view.StoryListView;

public interface StoryListPresenter extends Presenter<StoryListView>
{
    void changeSection(String section);

    void selectStory(StoryModel story);
}
