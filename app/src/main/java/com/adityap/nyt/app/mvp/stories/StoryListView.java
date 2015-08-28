package com.adityap.nyt.app.mvp.stories;

import com.adityap.nyt.app.mvp.core.View;

import java.util.List;

public interface StoryListView extends View
{
    void showLoading();

    void selectSection(int section);

    void displayStories(List<StoryModel> stories);

    void displayNoStoriesMessage();

    void displayError();

    void navigateToDetailsView();
}
