package com.adityap.nyt.app.mvp.stories;

import com.adityap.nyt.app.mvp.core.State;
import com.adityap.nyt.domain.model.story.Story;

import java.util.List;

public class StoryListState implements State<StoryListView>
{
    private static final int STATE_STORIES = 0;
    private static final int STATE_NO_STORIES = 1;
    private static final int STATE_ERROR = 2;
    private static final int STATE_LOADING = 3;

    private int state;
    private int section;
    private List<Story> stories;

    private StoryListState(int state, int section, List<Story> stories)
    {
        this.state = state;
        this.section = section;
        this.stories = stories;
    }

    public static StoryListState stories(int section, List<Story> stories)
    {
        return new StoryListState(STATE_STORIES, section, stories);
    }

    public static StoryListState noStories(int section)
    {
        return new StoryListState(STATE_NO_STORIES, section, null);
    }

    public static StoryListState error(int section)
    {
        return new StoryListState(STATE_ERROR, section, null);
    }

    public static StoryListState loading(int section)
    {
        return new StoryListState(STATE_LOADING, section, null);
    }

    public boolean isLoading()
    {
        return (state == STATE_LOADING);
    }

    public int getSection()
    {
        return section;
    }

    public List<Story> getStories()
    {
        return stories;
    }

    public void render(StoryListView view, StoryModelMapper mapper)
    {
        if (view != null)
        {
            view.selectSection(section);

            if (state == STATE_LOADING)
            {
                view.showLoading();
            }
            else
            {
                switch (state)
                {
                    case STATE_STORIES:
                        view.displayStories(mapper.map(stories));
                        break;
                    case STATE_NO_STORIES:
                        view.displayNoStoriesMessage();
                        break;
                    case STATE_ERROR:
                        view.displayError();
                        break;
                }
            }
        }
    }
}
