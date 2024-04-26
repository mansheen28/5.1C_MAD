package com.example.itube;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylist extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);
        recyclerView = findViewById(R.id.rvMyPlaylist);
        List<String> urls = retrieveAllVideoURLs();
        UrlAdapter urlAdapter = new UrlAdapter(urls);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(urlAdapter);
    }

    private List<String> retrieveAllVideoURLs() {
        List<String> urls = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_URLS,
                new String[]{DatabaseHelper.COLUMN_VIDEO_URL},
                null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String url = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_VIDEO_URL));
                urls.add(url);
            }
            cursor.close();
        }
        db.close();
        return urls;
    }

    private static class UrlAdapter extends RecyclerView.Adapter<MyPlaylist.UrlAdapter.UrlViewHolder> {

        private List<String> urlList;

        public UrlAdapter(List<String> urlList) {
            this.urlList = urlList;
        }

        @NonNull
        @Override
        public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_url, parent, false);
            return new UrlViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UrlViewHolder holder, int position) {
            String url = urlList.get(position);
            holder.bind(url);
        }

        @Override
        public int getItemCount() {
            return urlList.size();
        }

        // ViewHolder class
        public static class UrlViewHolder extends RecyclerView.ViewHolder {
            TextView textViewUrl;

            public UrlViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewUrl = itemView.findViewById(R.id.textViewUrl);
            }

            public void bind(String url) {
                textViewUrl.setText(url);
            }
        }
    }
}

