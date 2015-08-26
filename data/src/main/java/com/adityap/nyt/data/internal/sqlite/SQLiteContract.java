package com.adityap.nyt.data.internal.sqlite;

public abstract class SQLiteContract
{
    public static final String DATABASE_NAME = "nyt_demo.db";
    public static final int DATABASE_VERSION = 1;

    public static abstract class Storage
    {
        public static final String TABLE_NAME = "storage";

        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_VALUE = "value";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_KEY + " TEXT PRIMARY KEY, " +
                COLUMN_VALUE + " TEXT)";

        public static final String SQL_DROP_TABLE = "DROP TABLE " + TABLE_NAME;

        public static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
        public static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_KEY + " = ?";
    }

    public static abstract class Stories
    {
        public static final String TABLE_NAME = "stories";

        public static final String COLUMN_ID = "section_id";
        public static final String COLUMN_CONTENT = "story_list_json";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_CONTENT + " TEXT)";

        public static final String SQL_DROP_TABLE = "DROP TABLE " + TABLE_NAME;

        public static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
        public static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
    }
}
