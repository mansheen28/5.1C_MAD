package com.example.newsapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class VerticalNewsAdapter extends RecyclerView.Adapter<VerticalNewsAdapter.UrlViewHolder> {

    private List<String[]> list;
    private OnItemClickListener listener;

    private int res;

    public VerticalNewsAdapter(List<String[]> list, OnItemClickListener listener, int res) {
        this.list = list;
        this.listener = listener;
        this.res = res;
    }

    @NonNull
    @Override
    public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(res, parent, false);
        return new UrlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UrlViewHolder holder, int position) {
        String[] item = list.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UrlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_news_vertical;
        TextView tv_news_title_vertical, tv_news_desc_vertical;

        public UrlViewHolder(@NonNull View itemView) {
            super(itemView);
            img_news_vertical = itemView.findViewById(R.id.img_news_vertical);
            tv_news_title_vertical = itemView.findViewById(R.id.tv_news_title_vertical);
            tv_news_desc_vertical = itemView.findViewById(R.id.tv_news_desc_vertical);
            itemView.setOnClickListener(this);
        }

        public void bind(String[] item) {
            Picasso.get().load(item[0]).into(img_news_vertical);
            tv_news_title_vertical.setText(item[1]);
            tv_news_desc_vertical.setText(item[2]);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                String[] imageUrl = list.get(position);
                listener.onItemClick(imageUrl);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String[] item);
    }
}
