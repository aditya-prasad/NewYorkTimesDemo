package com.adityap.nyt.app.ui.flow.main.stories;

import com.adityap.nyt.app.mvp.stories.StoryModel;
import com.adityap.nyt.app.mvp.stories.StoryModelMapper;
import com.adityap.nyt.domain.model.story.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryModelMapperImpl implements StoryModelMapper
{
    @Override
    public StoryModel map(Story story)
    {
        return new StoryListItem(story);
    }

    @Override
    public List<StoryModel> map(List<Story> stories)
    {
        List<StoryModel> storyListItems = new ArrayList<>();
        for (Story story : stories)
        {
            storyListItems.add(map(story));
        }
        return storyListItems;
    }
}
