package com.adityap.nyt.app.mvp.details;

import com.adityap.nyt.app.mvp.core.View;

import java.util.List;

public interface StoryDetailsView extends View
{
    void initialize(List<StoryDetailsModel> stories, int initialPosition);
}
