package com.adityap.nyt.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.adityap.nyt.data.internal.sqlite.SQLiteContract;
import com.adityap.nyt.data.internal.sqlite.SQLiteManager;
import com.adityap.nyt.data.story.cache.StoryCache;
import com.adityap.nyt.domain.model.story.Story;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SQLiteStoryCache implements StoryCache
{
    private static final String TAG = "Storage";

    private SQLiteManager databaseManager;
    private Gson gson;

    public SQLiteStoryCache(SQLiteManager databaseManager, Gson gson)
    {
        this.databaseManager = databaseManager;
        this.gson = gson;
        Timber.v("SQLiteStoryCache Initialized");
    }

    @Override
    public Observable<List<Story>> getStories(int section)
    {
        return Observable
                .create(new Observable.OnSubscribe<List<Story>>()
                {
                    @Override
                    public void call(Subscriber<? super List<Story>> subscriber)
                    {
                        long startTime = System.currentTimeMillis();
                        SQLiteDatabase db = databaseManager.openConnection();

                        long connectionCreationTime = System.currentTimeMillis();

                        String[] projection = {SQLiteContract.Stories.COLUMN_CONTENT};
                        String selection = SQLiteContract.Stories.COLUMN_ID + " = ?";
                        String[] selectionArgs = {"" + section};

                        Cursor cursor = db
                                .query(SQLiteContract.Stories.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                        cursor.moveToFirst();

                        if (!cursor.isAfterLast())
                        {
                            String storyListJson = cursor.getString(0);

                            Type type = new TypeToken<List<Story>>()
                            {
                            }.getType();
                            List<Story> stories = gson.fromJson(storyListJson, type);
                            subscriber.onNext(stories);
                        }
                        else
                        {
                            Timber.d("Unable to find stories for section = " + section);
                            subscriber.onNext(new ArrayList<Story>());
                        }

                        long loadTime = System.currentTimeMillis();

                        cursor.close();
                        databaseManager.closeConnection();

                        long endTime = System.currentTimeMillis();

                        Timber.d("[TIME] Total = " + (endTime-startTime) + "ms");
                        Timber.d("[TIME] Open Connection = " + (connectionCreationTime-startTime) + "ms");
                        Timber.d("[TIME] Fetch data = " + (loadTime-connectionCreationTime) + "ms");
                        Timber.d("[TIME] Close Connection = " + (endTime-loadTime) + "ms");

                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void put(int section, List<Story> stories)
    {
        Observable
                .create(subscriber -> {
                    synchronized (TAG)
                    {
                        Timber.v("Stories.put() on thread = " + Thread.currentThread()
                                                                      .getName());

                        SQLiteDatabase db = databaseManager.openConnection();
                        db.beginTransactionNonExclusive();

                        SQLiteStatement statement = db
                                .compileStatement(SQLiteContract.Stories.SQL_DELETE);
                        statement.bindLong(1, section);
                        statement.execute();
                        statement.clearBindings();
                        statement.close();

                        statement = db.compileStatement(SQLiteContract.Stories.SQL_INSERT);
                        statement.bindLong(1, section);
                        statement.bindString(2, gson.toJson(stories));
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
                        Timber.d("[Insert] " + stories.size() + " in section = " + section);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Timber.e("Insert failed for section = " + section + " [" + e.getMessage() + "]");
                    }

                    @Override
                    public void onNext(Object o)
                    {

                    }
                });
    }
}
