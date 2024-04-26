package com.example.itube;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "itube_database.db";
    private static final int DATABASE_VERSION = 2;

    // Table names and column names
    public static final String TABLE_USERS = "user_accounts";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_URLS = "video_urls";
    public static final String COLUMN_URL_ID = "url_id";
    public static final String COLUMN_VIDEO_URL = "video_url";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FULL_NAME + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ")";

    private static final String CREATE_TABLE_URLS = "CREATE TABLE " + TABLE_URLS + "("
            + COLUMN_URL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_VIDEO_URL + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_URLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_URLS);
        onCreate(db);
    }
}
