package com.adityap.nyt.data.internal.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import timber.log.Timber;

public class SQLiteHelper extends SQLiteOpenHelper
{
    public SQLiteHelper(Context context)
    {
        super(context, SQLiteContract.DATABASE_NAME, null, SQLiteContract.DATABASE_VERSION);
        Timber.v("SQLiteHelper Initialized");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQLiteContract.Storage.SQL_CREATE_TABLE);
        db.execSQL(SQLiteContract.Stories.SQL_CREATE_TABLE);
        Timber.d("Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQLiteContract.Storage.SQL_DROP_TABLE);
        db.execSQL(SQLiteContract.Stories.SQL_DROP_TABLE);
        onCreate(db);
        Timber.d("Database Updated");
    }
}
