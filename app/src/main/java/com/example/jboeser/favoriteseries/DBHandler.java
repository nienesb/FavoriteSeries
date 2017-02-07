package com.example.jboeser.favoriteseries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.Application;

import static java.security.AccessController.getContext;


/**
 * Created by j.boeser on 7-2-2017.
 */

class DbHelper extends SQLiteOpenHelper{


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SeriesDB";

    private DbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    DbHelper mDbHelper = new DbHelper(getContext());

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SERIES_TABLE = "CREATE TABLE " +
                SeriesContract.SeriesDB.TABLE_NAME + "("
                + SeriesContract.SeriesDB._ID + " INTEGER PRIMARY KEY,"
                + SeriesContract.SeriesDB.COLUMN_NAME_TITLE + " TEXT," + ")";
        db.execSQL(CREATE_SERIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SeriesContract.SeriesDB.TABLE_NAME);
        onCreate(db);
    }




}
