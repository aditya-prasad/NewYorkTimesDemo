package com.adityap.nyt.app.ui.flow.main.details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adityap.nyt.app.mvp.details.StoryDetailsModel;

import java.util.List;

public class StoryDetailsPagerAdapter extends FragmentStatePagerAdapter
{
    private List<StoryDetailsModel> stories;

    public StoryDetailsPagerAdapter(FragmentManager fm, List<StoryDetailsModel> stories)
    {
        super(fm);
        this.stories = stories;
    }

    @Override
    public Fragment getItem(int position)
    {
        return StoryDetailsFragment.newInstance(stories.get(position));
    }

    @Override
    public int getCount()
    {
        return stories.size();
    }
}
