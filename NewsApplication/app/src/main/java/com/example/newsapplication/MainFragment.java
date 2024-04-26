package com.example.newsapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements VerticalNewsAdapter.OnItemClickListener, TopStoriesAdapter.OnStoryClickListener {
    private RecyclerView horizontalRecyclerView;
    private RecyclerView verticalRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        horizontalRecyclerView = view.findViewById(R.id.recyclerViewHorizontal);
        verticalRecyclerView = view.findViewById(R.id.recyclerViewVertical);
        verticalRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<String[]> newsList = new ArrayList<>();

        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});
        newsList.add(new String[]{"https://media.moddb.com/images/members/4/3333/3332693/ao_earth-from-space-western-400x400.jpg", "This is a title", "I have created the news application for 5.2C"});

        TopStoriesAdapter topStoriesAdapter = new TopStoriesAdapter(newsList, this);
        VerticalNewsAdapter verticalNewsAdapter = new VerticalNewsAdapter(newsList, this, R.layout.news_item_vertical);

        horizontalRecyclerView.setAdapter(topStoriesAdapter);
        verticalRecyclerView.setAdapter(verticalNewsAdapter);

        return view;
    }

    @Override
    public void onItemClick(String[] item) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, ItemFragment.newInstance(item));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
