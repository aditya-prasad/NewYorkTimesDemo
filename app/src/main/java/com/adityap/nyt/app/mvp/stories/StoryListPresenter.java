package com.adityap.nyt.app.mvp.stories;

import com.adityap.nyt.app.mvp.core.Presenter;

public interface StoryListPresenter extends Presenter<StoryListView>
{
    void changeSection(int section);

    void refreshSection(int section);

    void selectStory(String title, String author);
}
