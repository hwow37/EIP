package com.example.hk.eip.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {
    private static final String DATABASE_NAME = "wordbook.db";
    private static final int DATABASE_VERSION = 1;

    public static SQLiteDatabase mDB;

    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        // 생성자
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DataBases.CreateDB._CREATE);
        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DataBases.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String name, String meaning, int check_word) {
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.NAME, name);
        values.put(DataBases.CreateDB.MEANING, meaning);
        values.put(DataBases.CreateDB.CHECK_WORD, check_word);
        return mDB.insert(DataBases.CreateDB._TABLENAME, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String name, String meaning, int check_word) {
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.NAME, name);
        values.put(DataBases.CreateDB.MEANING, meaning);
        values.put(DataBases.CreateDB.CHECK_WORD, check_word);
        return mDB.update(DataBases.CreateDB._TABLENAME, values, "_id=" + id, null) > 0;
    }

    // Delete ID
    public boolean deleteColumn(long id) {
        return mDB.delete(DataBases.CreateDB._TABLENAME, "_id=" + id, null) > 0;
    }

    // Delete mean
    public boolean deleteColumn(String meaning) {
        return mDB.delete(DataBases.CreateDB._TABLENAME, "meaning=" + meaning, null) > 0;
    }

    // Select All
    public Cursor getAllColumns() {
        return mDB.query(DataBases.CreateDB._TABLENAME, null, null, null, null, null, null);
    }

    // ID
    public Cursor getColumn(long id) {
        Cursor c = mDB.query(DataBases.CreateDB._TABLENAME, null, "_id=" + id, null, null, null, null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }

    // rawQuery
    public Cursor getMatchName(String name) {
        Cursor c = mDB.rawQuery("select * from address where name=" + "'" + name + "'", null);
        return c;
    }
    // check word
    public boolean checkWordState(long id, int check_word) {
        ContentValues values = new ContentValues();
        if(check_word==0) {
            values.put(DataBases.CreateDB.CHECK_WORD, 1);
        }else{
            values.put(DataBases.CreateDB.CHECK_WORD, 0);
        }
        return mDB.update(DataBases.CreateDB._TABLENAME, values, "_id=" + id, null) > 0;
    }
}