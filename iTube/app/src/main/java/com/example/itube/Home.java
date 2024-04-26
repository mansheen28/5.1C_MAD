package com.example.itube;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    EditText urlEditText;
    Button myPlaylistButton, playButton, addToPlaylistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        urlEditText = findViewById(R.id.editTextUrl);
        myPlaylistButton = findViewById(R.id.buttonMyPlaylist);
        playButton = findViewById(R.id.buttonPlay);
        addToPlaylistButton = findViewById(R.id.buttonAddToPlaylist);

        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyPlaylist();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToPlaylist();
            }
        });
    }

    private void openMyPlaylist() {
        Intent intent = new Intent(Home.this, MyPlaylist.class);
        startActivity(intent);
    }

    private void playVideo() {
        String url = urlEditText.getText().toString();
        if (url.isEmpty()) {
            Toast.makeText(this, "Please enter the URL", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Home.this, Player.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    private void addToPlaylist() {
        String url = urlEditText.getText().toString();
        if (url.isEmpty()) {
            Toast.makeText(this, "Please enter the URL", Toast.LENGTH_SHORT).show();
        } else {
            insertURLIntoDatabase(url);
        }
    }

    private void insertURLIntoDatabase(String url) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_VIDEO_URL, url);

        db.insert(DatabaseHelper.TABLE_URLS, null, values);
        db.close();
        Toast.makeText(this, "Added to Playlist", Toast.LENGTH_SHORT).show();
    }
}
