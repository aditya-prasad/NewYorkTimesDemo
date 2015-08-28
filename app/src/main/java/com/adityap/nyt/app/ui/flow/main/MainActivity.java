package com.adityap.nyt.app.ui.flow.main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.adityap.nyt.R;
import com.adityap.nyt.app.ui.core.BaseActivity;
import com.adityap.nyt.app.ui.core.FragmentUtils;
import com.adityap.nyt.app.ui.flow.main.details.StoryDetailsContainerFragment;
import com.adityap.nyt.app.ui.flow.main.stories.StoryListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
{
    public static final int SCREEN_LIST = 0;
    public static final int SCREEN_DETAILS = 1;

    private static final String ARG_ACTIVE_SCREEN = "Arg_active_screen";

    /* UI Elements */
    @Bind(R.id.tb_main)
    Toolbar toolbar;

    /* State */
    private int activeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        if (savedInstanceState == null)
        {
            activeScreen = SCREEN_LIST;
        }
        else
        {
            activeScreen = savedInstanceState.getInt(ARG_ACTIVE_SCREEN);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        switch (activeScreen)
        {
            case SCREEN_LIST:
                FragmentUtils.changeFragment(getSupportFragmentManager(), R.id.fl_main,
                        StoryListFragment.newInstance(), FragmentTransaction.TRANSIT_NONE);
                break;
            case SCREEN_DETAILS:
                FragmentUtils.changeFragment(getSupportFragmentManager(), R.id.fl_main,
                        StoryDetailsContainerFragment.newInstance(), FragmentTransaction.TRANSIT_NONE);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt(ARG_ACTIVE_SCREEN, activeScreen);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed()
    {
        switch (activeScreen)
        {
            case SCREEN_DETAILS:
                FragmentUtils.changeFragment(getSupportFragmentManager(), R.id.fl_main, StoryListFragment.newInstance(), FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                break;
            case SCREEN_LIST:
                finish();
                break;
        }
    }

    public void setActiveScreen(int activeScreen)
    {
        this.activeScreen = activeScreen;
    }
}
