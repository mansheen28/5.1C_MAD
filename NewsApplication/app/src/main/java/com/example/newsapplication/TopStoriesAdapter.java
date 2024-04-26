package com.example.newsapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.StoryViewHolder> {

    private List<String[]> storiesList;
    private OnStoryClickListener listener;

    public TopStoriesAdapter(List<String[]> storiesList, OnStoryClickListener listener) {
        this.storiesList = storiesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_story_item, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        String[] story = storiesList.get(position);
        holder.bind(story);
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        ImageView storyImageView;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyImageView = itemView.findViewById(R.id.img_story_item);
            itemView.setOnClickListener(this);
        }

        public void bind(String[] story) {
            Picasso.get().load(story[0]).into(storyImageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                String[] storyData = storiesList.get(position);
                listener.onItemClick(storyData);
            }
        }
    }

    public interface OnStoryClickListener {
        void onItemClick(String[] storyData);
    }
}
