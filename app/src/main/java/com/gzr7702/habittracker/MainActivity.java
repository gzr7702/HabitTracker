package com.gzr7702.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gzr7702.habittracker.data.HabitContract.HabitEntry;
import com.gzr7702.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
        insert a new habit record
     */
    public long insertHabit(String habit) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habit);
        return db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /*
        Return one babit record from the database
     */
    public String getHabitReport(int id) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String habitString;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_CURRENT_DATE,
                HabitEntry.COLUMN_COMPLETED_TODAY
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        try {

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int currentDateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_CURRENT_DATE);
            int completedColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_COMPLETED_TODAY);

            String currentName = cursor.getString(nameColumnIndex);
            String currentDate = cursor.getString(currentDateColumnIndex);
            int completed = cursor.getInt(completedColumnIndex);

            StringBuilder sb = new StringBuilder();
            sb.append((idColumnIndex + " - " +
                  currentName + " - " +
                  currentDate + " - " +
                  completed));
            habitString = sb.toString();
        } finally {
            cursor.close();
        }
        return habitString;
    }

}
