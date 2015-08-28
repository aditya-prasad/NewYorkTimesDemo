package com.adityap.nyt.app.ui.flow.main.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adityap.nyt.R;
import com.adityap.nyt.app.mvp.details.StoryDetailsModel;
import com.adityap.nyt.app.ui.core.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryDetailsFragment extends BaseFragment
{
    private static final String ARG_STORY = "arg_story";

    /* UI Elements */
    @Bind(R.id.tv_details_title)
    TextView title;

    @Bind(R.id.tv_details_author)
    TextView author;

    @Bind(R.id.tv_details_date)
    TextView date;

    @Bind(R.id.ll_details_image)
    LinearLayout imageContainer;

    @Bind(R.id.iv_details_image)
    ImageView image;

    @Bind(R.id.tv_details_imageCaption)
    TextView imageCaption;

    @Bind(R.id.tv_details_summary)
    TextView summary;

    @Bind(R.id.tv_details_url)
    TextView url;

    private StoryDetails storyDetails;

    /* Static Factory */
    public static StoryDetailsFragment newInstance(StoryDetailsModel story)
    {
        StoryDetailsFragment fragment = new StoryDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_STORY, story);
        fragment.setArguments(bundle);
        return fragment;
    }

    /* Lifecycle methods */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        StoryDetails storyDetails = (StoryDetails) getArguments().getSerializable(ARG_STORY);
        if (storyDetails != null)
        {
            title.setText(storyDetails.getTitle());
            author.setText(storyDetails.getAuthor());
            date.setText(storyDetails.getDate());
            summary.setText(storyDetails.getSummary());

            url.setText(Html.fromHtml("<a href='" + storyDetails.getUrl() + "'>" + getResources()
                    .getString(R.string.message_read_full_story) + "</a>"));
            url.setMovementMethod(LinkMovementMethod.getInstance());

            if (storyDetails.isImageAvailable())
            {
                imageContainer.setVisibility(View.VISIBLE);

                Picasso
                        .with(getActivity())
                        .load(storyDetails.getImageUrl())
                        .placeholder(R.drawable.news)
                        .error(R.drawable.news)
                        .fit()
                        .centerInside()
                        .into(image);

                if (storyDetails.isImageCaptionAvailable())
                {
                    imageCaption.setText(storyDetails.getImageCaption());
                    imageCaption.setVisibility(View.VISIBLE);
                }
                else
                {
                    imageCaption.setVisibility(View.GONE);
                }
            }
            else
            {
                imageContainer.setVisibility(View.GONE);
            }
        }
    }
}
