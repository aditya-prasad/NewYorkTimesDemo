package com.adityap.nyt.app.ui.flow.main.details;

import com.adityap.nyt.app.mvp.details.StoryDetailsModel;
import com.adityap.nyt.app.mvp.details.StoryDetailsModelMapper;
import com.adityap.nyt.domain.model.story.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryDetailsModelMapperImpl implements StoryDetailsModelMapper
{
    @Override
    public StoryDetailsModel map(Story story)
    {
        return new StoryDetails(story);
    }

    @Override
    public List<StoryDetailsModel> map(List<Story> stories)
    {
        List<StoryDetailsModel> models = new ArrayList<>(stories.size());
        for(Story story:stories)
        {
            models.add(map(story));
        }
        return models;
    }
}
