package com.adityap.nyt.app.mvp.view;

import com.adityap.nyt.app.mvp.core.View;
import com.adityap.nyt.app.mvp.model.StoryModel;

import java.util.List;

public interface StoryListView extends View
{
    void showLoading();

    void hideLoading();

    void displayStories(List<StoryModel> stories);

    void displayNoStoriesMessage();

    void displayError();

    void navigateToDetailsView();
}
