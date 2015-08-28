package com.adityap.nyt.app.ui.flow.main.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adityap.nyt.R;
import com.adityap.nyt.app.ScopeManager;
import com.adityap.nyt.app.mvp.details.StoryDetailsModel;
import com.adityap.nyt.app.mvp.details.StoryDetailsPresenter;
import com.adityap.nyt.app.mvp.details.StoryDetailsView;
import com.adityap.nyt.app.mvp.stories.StoryModel;
import com.adityap.nyt.app.ui.core.BaseFragment;
import com.adityap.nyt.app.ui.flow.main.MainActivity;
import com.adityap.nyt.app.ui.flow.main.details.di.StoryDetailsModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class StoryDetailsContainerFragment extends BaseFragment implements StoryDetailsView
{
    /* UI Elements */
    @Bind(R.id.vp_details_container)
    ViewPager container;

    /* Presenter */
    @Inject
    StoryDetailsPresenter presenter;

    /* Static Factory */
    public static StoryDetailsContainerFragment newInstance()
    {
        return new StoryDetailsContainerFragment();
    }

    /* Lifecycle Methods */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ScopeManager.getInstance()
                .storyComponent()
                .plus(new StoryDetailsModule())
                .injectIn(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_details_container, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ((MainActivity) getActivity()).setActiveScreen(MainActivity.SCREEN_DETAILS);
        presenter.attachView(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        presenter.detachView();
    }

    /* View Methods */
    @Override
    public void initialize(List<StoryDetailsModel> stories, int initialPosition)
    {
        container.setAdapter(new StoryDetailsPagerAdapter(getChildFragmentManager(), stories));
        container.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i2)
            {
            }

            @Override
            public void onPageSelected(int i)
            {
                container.setCurrentItem(i);
                presenter.setActivePosition(i);
            }

            @Override
            public void onPageScrollStateChanged(int i)
            {
            }
        });

        container.setCurrentItem(initialPosition);
    }
}
