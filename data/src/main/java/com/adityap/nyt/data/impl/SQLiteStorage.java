package com.adityap.nyt.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.adityap.nyt.data.internal.sqlite.SQLiteContract;
import com.adityap.nyt.data.internal.sqlite.SQLiteManager;
import com.adityap.nyt.domain.core.Storage;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SQLiteStorage implements Storage
{
    private static final String TAG = "Storage";

    private SQLiteManager databaseManager;
    private Gson gson;
    private Map<String, String> memoryStore;

    public SQLiteStorage(SQLiteManager databaseManager, Gson gson)
    {
        this.databaseManager = databaseManager;
        this.gson = gson;
        this.memoryStore = new HashMap<>();
        Timber.v("Storage Initialized");
    }

    @Override
    public void put(String key, String value)
    {
        memoryStore.put(key, value);
        Observable
                .create(subscriber -> {
                    synchronized (TAG)
                    {
                        Timber.v("Storage.put() on thread = " + Thread.currentThread()
                                                                           .getName());

                        SQLiteDatabase db = databaseManager.openConnection();
                        db.beginTransactionNonExclusive();

                        SQLiteStatement statement = db
                                .compileStatement(SQLiteContract.Storage.SQL_DELETE);
                        statement.bindString(1, key);
                        statement.execute();
                        statement.clearBindings();
                        statement.close();

                        statement = db.compileStatement(SQLiteContract.Storage.SQL_INSERT);
                        statement.bindString(1, key);
                        statement.bindString(2, value);
                        statement.execute();
                        statement.clearBindings();
                        statement.close();

                        db.setTransactionSuccessful();
                        db.endTransaction();
                        databaseManager.closeConnection();

                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>()
                {
                    @Override
                    public void onCompleted()
                    {
                        Timber.d("[Insert] " + key + " = " + value);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Timber.e("Insert failed for key = " + key + " [" + e.getMessage() + "]");
                    }

                    @Override
                    public void onNext(Object o)
                    {

                    }
                });

    }

    @Override
    public String get(String key)
    {
        String value = memoryStore.get(key);
        if (value == null)
        {
            try
            {
                SQLiteDatabase db = databaseManager.openConnection();

                String[] projection = {SQLiteContract.Storage.COLUMN_VALUE};
                String selection = SQLiteContract.Storage.COLUMN_KEY + " = ?";
                String[] selectionArgs = {key};

                Cursor cursor = db
                        .query(SQLiteContract.Storage.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                cursor.moveToFirst();

                if (!cursor.isAfterLast())
                {
                    value = cursor.getString(0);
                }
                else
                {
                    Timber.d("Unable to find '" + key + "' in storage");
                }

                cursor.close();
                databaseManager.closeConnection();
            }
            catch (Exception e)
            {
                Timber.e("Error while reading key = " + key + " from storage [" + e
                        .getMessage() + "]");
            }
        }
        return value;
    }

    @Override
    public void put(String key, Object value)
    {
        put(key, gson.toJson(value));
    }

    @Override
    public <T> T get(String key, Class<T> type)
    {
        String valueJson = get(key);
        T value = null;
        try
        {
            value = gson.fromJson(valueJson, type);
        }
        catch (Exception e)
        {
            Timber.e("Unable to parse json into object for key = " + key + " [" + e
                    .getMessage() + "]");
            value = null;
        }
        return value;
    }
}
