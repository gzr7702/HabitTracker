package com.gzr7702.habittracker.data;
import android.provider.BaseColumns;

/*
    Define Db schema for Habit DB
 */

public class HabitContract{

    // Prevent any mistaken instances
    private HabitContract() {}

    // Inner class that defines the table contents of the table
    public static final class HabitEntry implements BaseColumns {

        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "habit";
        public static final String COLUMN_HABIT_NAME = "habit_name";
        // Today's date
        public static final String COLUMN_CURRENT_DATE = "current_date";
        // Boolean to track if habit was completed today
        public static final String COLUMN_COMPLETED_TODAY = "completed_today";
        public static final int NOT_COMPLETED = 0;
        public static final int COMPLETED = 0;

    }
}
