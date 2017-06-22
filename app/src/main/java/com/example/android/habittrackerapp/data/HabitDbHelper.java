package com.example.android.habittrackerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Created by Edvinas on 21/06/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private Context context;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + HabitEntry.HABIT_TABLE_NAME + "(" +
                HabitEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitEntry.COLUMN_NAME + " TEXT NOT  NULL," +
                HabitEntry.COLUMN_START_DATE + " INT NOT NULL DEFAULT 0, " +
                HabitEntry.COLUMN_DIFFICULTY + " INT NOT NULL DEFAULT 0)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HabitContract.HabitEntry.HABIT_TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertRow(ContentValues contentValues) {
        db = getWritableDatabase();
        db.insert(HabitContract.HabitEntry.HABIT_TABLE_NAME, null, contentValues);
    }

    public Cursor readRows(int difficulty) {
        Cursor cursor;
        String selection = HabitEntry.COLUMN_DIFFICULTY + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(difficulty)};
        db = getReadableDatabase();
        cursor = db.query(true, HabitContract.HabitEntry.HABIT_TABLE_NAME, null, selection,
                selectionArgs, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    public int getColumnCount() {
        String[] tableColumns = new String[]{HabitEntry.COLUMN_ID};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(HabitEntry.HABIT_TABLE_NAME, tableColumns, null, null, null, null, null);
        int columnCount = 0;
        try {
            columnCount = cursor.getCount();
            Toast.makeText(context, Integer.toString(cursor.getCount()), Toast.LENGTH_SHORT).show();
        } finally {
            cursor.close();
            db.close();
        }
        return columnCount;
    }

}
