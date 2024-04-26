package com.example.newsapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements VerticalNewsAdapter.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MainFragment())
                .commit();
    }

    @Override
    public void onItemClick(String[] url) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ItemFragment.newInstance(url))
                .addToBackStack(null)
                .commit();
    }
}