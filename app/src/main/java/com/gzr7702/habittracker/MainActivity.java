package com.gzr7702.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gzr7702.habittracker.data.HabitContract.HabitEntry;
import com.gzr7702.habittracker.data.HabitDbHelper;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private HabitDbHelper mDbHelper;

    String[] habits = {"Brush teeth", "Walk the dog", "Do the dishes", "Go for a walk"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitDbHelper(this);

        for(int i = 0; i < habits.length; i++) {
            insertHabit(habits[i]);
        }

        ArrayList<String> data = extractData();

        Log.v(LOG_TAG, data.toString());
    }

    /*
        insert a new habit record
     */
    public long insertHabit(String habit) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String today = DateFormat.getDateTimeInstance().format(new Date());

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habit);
        values.put(HabitEntry.COLUMN_CURRENT_DATE, today);
        // Added for completeness, but not really necessary
        values.put(HabitEntry.COLUMN_COMPLETED_TODAY, 0);
        return db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /*
        Return all habit records from the database
     */
    public Cursor getAllHabits() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_CURRENT_DATE,
                HabitEntry.COLUMN_COMPLETED_TODAY
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        return cursor;
    }

    private ArrayList<String> extractData() {
        String name;
        String date;
        String completed;
        ArrayList<String> allData = new ArrayList<>();

        Cursor c = getAllHabits();
        int nameIndex = c.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
        int dateIndex = c.getColumnIndex(HabitEntry.COLUMN_CURRENT_DATE);
        int completedIndex = c.getColumnIndex(HabitEntry.COLUMN_COMPLETED_TODAY);

        while (c.moveToNext()) {
            name = c.getString(nameIndex);
            date = c.getString(dateIndex);
            completed = c.getString(completedIndex);

            allData.add(name + " " + date + " " + completed);
        }
        return allData;
    }

}
