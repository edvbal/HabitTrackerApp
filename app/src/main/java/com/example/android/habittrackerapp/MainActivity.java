package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper dbHelper = new HabitDbHelper(this);

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME, "Workout");
        values.put(HabitEntry.COLUMN_START_DATE, "21/06/2017");
        values.put(HabitEntry.COLUMN_DIFFICULTY, HabitEntry.DIFFICULTY_HARD);
        dbHelper.insertRow(values);
        Cursor cursor = dbHelper.readRow(5);
        int nameIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME);
        String name = cursor.getString(nameIndex);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        cursor.close();
    }
}
