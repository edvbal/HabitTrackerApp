package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by Edvinas on 21/06/2017.
 */

public final class HabitContract {
    public HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {
        // Name of database table for habits
        public static final String HABIT_TABLE_NAME = "habits";

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_DIFFICULTY = "difficulty";

        // Possible values for the difficulty
        public static final int DIFFICULTY_HARD = 2;
        public static final int DIFFICULTY_MEDIUM = 1;
        public static final int DIFFICULTY_EASY = 0;

    }
}
