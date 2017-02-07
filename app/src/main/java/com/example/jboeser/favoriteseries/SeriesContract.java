package com.example.jboeser.favoriteseries;

import android.provider.BaseColumns;

/**
 * Created by j.boeser on 7-2-2017.
 */

public class SeriesContract {

    private SeriesContract () {
    }

    public static class SeriesDB implements BaseColumns {
        public static final String TABLE_NAME = "series";
        public static final String COLUMN_NAME_TITLE = "title";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SeriesDB.TABLE_NAME + " (" +
                    SeriesDB._ID + " INTEGER PRIMARY KEY," +
                    SeriesDB.COLUMN_NAME_TITLE + " TEXT,) ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SeriesDB.TABLE_NAME;

}
