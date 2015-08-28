package com.adityap.nyt.app.ui.flow.main.stories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adityap.nyt.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder>
{
    private Context context;
    private List<StoryListItem> storyListItems;
    private StoryClickListener storyClickListener;

    public StoryAdapter(Context context, List<StoryListItem> storyListItems, StoryClickListener storyClickListener)
    {
        this.context = context;
        this.storyListItems = storyListItems;
        this.storyClickListener = storyClickListener;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        StoryViewHolder storyViewHolder = new StoryViewHolder(view);
        return storyViewHolder;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position)
    {
        StoryListItem currentItem = storyListItems.get(position);
        holder.render(currentItem);
    }

    @Override
    public int getItemCount()
    {
        return storyListItems.size();
    }

    public interface StoryClickListener
    {
        void onStoryClicked(String title, String author);
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.ll_storyItem)
        LinearLayout story;

        @Bind(R.id.tv_storyItem_title)
        TextView title;

        @Bind(R.id.iv_storyItem_icon)
        ImageView icon;

        @Bind(R.id.tv_storyItem_author)
        TextView author;

        @Bind(R.id.tv_storyItem_publishDate)
        TextView publishDate;

        public StoryViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);

            story.setOnClickListener(v -> {
                if (storyClickListener != null)
                {
                    storyClickListener
                            .onStoryClicked(title.getText().toString(), author.getText()
                                                                              .toString());
                }
            });
        }

        public void render(StoryListItem storyListItem)
        {
            title.setText(storyListItem.getTitle());
            author.setText(storyListItem.getAuthor());
            publishDate.setText(storyListItem.getPublishDate());

            Picasso
                    .with(context)
                    .load(storyListItem.getImageUrl())
                    .placeholder(R.drawable.news)
                    .error(R.drawable.news)
                    .fit()
                    .centerInside()
                    .into(icon);
        }
    }
}
