package com.adityap.nyt.app.mvp.details;

import com.adityap.nyt.app.mvp.core.State;
import com.adityap.nyt.domain.model.story.Story;

import java.util.List;

public class StoryDetailsState implements State<StoryDetailsView>
{
    public static final int STATE_STORY_DETAILS = 0;

    private int state;
    private List<Story> stories;
    private int activePosition;

    private StoryDetailsState(int state, List<Story> stories, int activePosition)
    {
        this.state = state;
        this.stories = stories;
        this.activePosition = activePosition;
    }

    public static StoryDetailsState storyDetails(List<Story> stories, int activePosition)
    {
        return new StoryDetailsState(STATE_STORY_DETAILS, stories, activePosition);
    }

    public void newPosition(int position)
    {
        this.activePosition = position;
    }

    public List<Story> getStories()
    {
        return stories;
    }

    public int getActivePosition()
    {
        return activePosition;
    }

    public void render(StoryDetailsView view, StoryDetailsModelMapper mapper)
    {
        if(view != null)
        {
            view.initialize(mapper.map(stories), activePosition);
        }
    }
}
