package com.myapptest.detetrensfercelenderpp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface CalendarDaoC {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CalendarEntryC entry);

    @Update
    void update(CalendarEntryC entry);

    @Delete
    void delete(CalendarEntryC entry);

    @Query("SELECT * FROM calendar_entries ORDER BY id DESC")
    LiveData<List<CalendarEntryC>> getAllDailyData();

    // ✅ এই মেথডটাই মিসিং ছিল — এখন ঠিকভাবে দেওয়া হলো
    @Query("SELECT * FROM calendar_entries WHERE date = :date")
    LiveData<List<CalendarEntryC>> getDataByDate(String date);

    @Query("SELECT * FROM calendar_entries WHERE date = :date LIMIT 1")
    LiveData<CalendarEntryC> getEntryByDate(String date); // ✅ LiveData ব্যবহার করা হয়েছে

    @Query("DELETE FROM calendar_entries")
    void clearAllDelyEntries();

    //New Methods ///////////////////////////////////////////////////

    // ✅ ১ মাসের পুরনো এন্ট্রি বের করা
    @Query("SELECT * FROM calendar_entries WHERE date <= date('now', '-1 month')")
    List<CalendarEntryC> getOldEntries();


    // ✅ মাসিক ডাটার জন্য নতুন এন্ট্রি যোগ করা
    @Insert
    void insertMonthlyEntries(List<CalendarEntryC> entries);


    // ✅ ১ মাসের পুরনো ডাটা মুছে ফেলা
    @Query("DELETE FROM calendar_entries WHERE date <= date('now', '-1 month')")
    void deleteOldEntries();



 /*   // ✅ মাসিক ডাটা ফেরত পাওয়া
    @Query("SELECT * FROM calendar_entries ORDER BY date ASC")
    LiveData<List<MonthlyEntryC>> getAllMonthlyEntries();*/


    @Query("SELECT * FROM CALENDAR_ENTRIES")
    LiveData<List<CalendarEntryC>> getAllData(); // UI এর জন্য Safe ✅


}
