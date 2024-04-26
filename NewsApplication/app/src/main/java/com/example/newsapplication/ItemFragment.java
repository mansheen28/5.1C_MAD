package com.example.newsapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends Fragment implements VerticalNewsAdapter.OnItemClickListener {

    public static ItemFragment newInstance(String[] selectedItem) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putStringArray("selectedItem", selectedItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item, container, false);
        ImageView imageView = rootView.findViewById(R.id.imageView2);
        TextView titleTextView = rootView.findViewById(R.id.tv_title);
        TextView descriptionTextView = rootView.findViewById(R.id.tv_description);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_vertical_news);

        List<String[]> topStories = new ArrayList<>();

        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        topStories.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});


        String[] selectedItem = getArguments().getStringArray("selectedItem");
        Picasso.get().load(selectedItem[0]).into(imageView);
        titleTextView.setText(selectedItem[1]);
        descriptionTextView.setText(selectedItem[2]);

        VerticalNewsAdapter verticalNewsAdapter = new VerticalNewsAdapter(topStories, this, R.layout.news_item_horizontal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(verticalNewsAdapter);

        return rootView;
    }

    @Override
    public void onItemClick(String[] item) {
        // Handle item click if needed
    }
}
