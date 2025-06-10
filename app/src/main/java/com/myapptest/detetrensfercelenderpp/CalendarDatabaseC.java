package com.myapptest.detetrensfercelenderpp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {CalendarEntryC.class}, version = 1, exportSchema = false)
@Database(entities = {CalendarEntryC.class, MonthlyEntryC.class}, version = 1, exportSchema = false)
public abstract class CalendarDatabaseC extends RoomDatabase {
    private static volatile CalendarDatabaseC INSTANCE;

    public abstract CalendarDaoC calendarDao();
    public abstract MonthlyDataDao monthlyDataDao();

    public static CalendarDatabaseC getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CalendarDatabaseC.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CalendarDatabaseC.class, "calendar_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
