package com.gzr7702.habittracker.data;
import android.provider.BaseColumns;

/*
    Define Db schema for Habit DB
 */

public class HabitContract{

    // Inner class that defines the table contents of the table
    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";

        public static final String COLUMN_HABIT_ID = "habit_id";

        public static final String COLUMN_HABIT_NAME = "habit_name";

        // Today's date
        public static final String COLUMN_DATE_TODAY = "date_today";

        // Boolean to track if habit was completed today
        public static final String COLUMN_COMPLETED_TODAY = "completed_today";

    }
}
