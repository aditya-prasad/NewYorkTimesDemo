package com.adityap.nyt.mvp.view;

import com.adityap.nyt.mvp.core.View;
import com.adityap.nyt.mvp.model.StoryModel;

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
