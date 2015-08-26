package com.adityap.nyt.data.internal.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

import timber.log.Timber;

public class SQLiteManager
{
    private SQLiteHelper sqliteHelper;
    private SQLiteDatabase database;
    private AtomicInteger connectionCount;

    public SQLiteManager(Context context)
    {
        sqliteHelper = new SQLiteHelper(context);
        connectionCount = new AtomicInteger(0);
        Timber.v("SQLiteManager Initialized");
    }

    public synchronized SQLiteDatabase openConnection()
    {
        if(connectionCount.incrementAndGet() == 1)
        {
            database = sqliteHelper.getWritableDatabase();
        }
        return database;
    }

    public synchronized void closeConnection()
    {
        if(connectionCount.decrementAndGet() == 0)
        {
            database.close();
            database = null;
        }
    }
}
