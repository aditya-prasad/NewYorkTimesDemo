package com.adityap.nyt.app.ui.flow.main.stories;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.adityap.nyt.R;
import com.adityap.nyt.app.ScopeManager;
import com.adityap.nyt.app.mvp.stories.StoryListPresenter;
import com.adityap.nyt.app.mvp.stories.StoryListView;
import com.adityap.nyt.app.mvp.stories.StoryModel;
import com.adityap.nyt.app.ui.core.BaseFragment;
import com.adityap.nyt.app.ui.core.FragmentUtils;
import com.adityap.nyt.app.ui.flow.main.MainActivity;
import com.adityap.nyt.app.ui.flow.main.details.StoryDetailsContainerFragment;
import com.adityap.nyt.app.ui.flow.main.stories.di.StoryListModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class StoryListFragment extends BaseFragment implements StoryListView, StoryAdapter.StoryClickListener
{
    /* UI Elements */
    @Bind(R.id.ll_storyList_content)
    LinearLayout content;

    @Bind(R.id.ll_storyList_noStoriesMessage)
    LinearLayout noStoriesMessage;

    @Bind(R.id.ll_storyList_loading)
    LinearLayout loading;

    @Bind(R.id.s_storyList_sections)
    Spinner spinner;

    @Bind(R.id.rv_storyList)
    RecyclerView storyList;

    @Bind(R.id.srl_storyList)
    SwipeRefreshLayout refresh;

    /* Presenter */
    @Inject
    StoryListPresenter presenter;

    /* Static Factory */
    public static StoryListFragment newInstance()
    {
        return new StoryListFragment();
    }

    /* Lifecycle Methods */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ScopeManager.getInstance()
                    .appComponent()
                    .plus(new StoryListModule())
                    .injectIn(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initSpinner();
        initSwipeRefresh();
        initRecyclerView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ((MainActivity) getActivity()).setActiveScreen(MainActivity.SCREEN_LIST);
        presenter.attachView(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        presenter.detachView();
    }

    /* Listeners */
    @Override
    public void onStoryClicked(String title, String author)
    {
        presenter.selectStory(title, author);
    }

    /* View Methods */
    @Override
    public void showLoading()
    {
        loading.setVisibility(View.VISIBLE);

        content.setVisibility(View.GONE);
        noStoriesMessage.setVisibility(View.GONE);
        refresh.setRefreshing(false);
    }

    @Override
    public void selectSection(int section)
    {
        spinner.setSelection(section - 1);
    }

    @Override
    public void displayStories(List<StoryModel> stories)
    {
        content.setVisibility(View.VISIBLE);

        noStoriesMessage.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        refresh.setRefreshing(false);

        List<StoryListItem> storyListItems = new ArrayList<>();
        for (StoryModel storyModel : stories)
        {
            storyListItems.add((StoryListItem) storyModel);
        }

        storyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        storyList.setAdapter(new StoryAdapter(getActivity(), storyListItems, this));
    }

    @Override
    public void displayNoStoriesMessage()
    {
        noStoriesMessage.setVisibility(View.VISIBLE);

        content.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        refresh.setRefreshing(false);
    }

    @Override
    public void displayError()
    {
        loading.setVisibility(View.GONE);
        refresh.setRefreshing(false);
        Snackbar.make(getView(), R.string.message_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void navigateToDetailsView()
    {
        FragmentUtils.changeFragment(getActivity().getSupportFragmentManager(), R.id.fl_main, StoryDetailsContainerFragment.newInstance(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    /* Helper Methods */
    private void initRecyclerView()
    {
        storyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        storyList.setAdapter(new StoryAdapter(getActivity(), new ArrayList<StoryListItem>(), this));
    }

    private void initSpinner()
    {
        ArrayAdapter<Section> adapter = new ArrayAdapter<Section>(getActivity(), android.R.layout.simple_spinner_item, Section
                .getSections());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (presenter != null)
                {
                    presenter.changeSection(((Section) parent.getItemAtPosition(position))
                            .getCode());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }

    private void initSwipeRefresh()
    {
        refresh.setOnRefreshListener(() -> {
            if (presenter != null)
            {
                presenter.refreshSection(((Section) spinner.getSelectedItem()).getCode());
            }
            else
            {
                refresh.setRefreshing(false);
            }
        });
    }
}
