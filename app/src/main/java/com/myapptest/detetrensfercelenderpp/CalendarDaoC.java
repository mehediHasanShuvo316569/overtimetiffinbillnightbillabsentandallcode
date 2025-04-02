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


    @Query("SELECT * FROM calendar_entries ORDER BY date ASC")
    LiveData<List<CalendarEntryC>> getAllEntries(); // ✅ LiveData ব্যবহার করা হয়েছে



    @Query("SELECT * FROM calendar_entries WHERE date = :date LIMIT 1")
    LiveData<CalendarEntryC> getEntryByDate(String date); // ✅ LiveData ব্যবহার করা হয়েছে


    @Query("DELETE FROM calendar_entries")
    void clearAllEntries();



    //New Methods ///////////////////////////////////////////////////


    // ✅ ১ মাসের পুরনো এন্ট্রি বের করা
    @Query("SELECT * FROM calendar_entries WHERE date <= date('now', '-1 month')")
    List<CalendarEntryC> getOldEntries();


    // ✅ মাসিক ডাটার জন্য নতুন এন্ট্রি যোগ করা
    @Insert
    void insertMonthlyEntries(List<MonthlyEntryC> entries);


    // ✅ ১ মাসের পুরনো ডাটা মুছে ফেলা
    @Query("DELETE FROM calendar_entries WHERE date <= date('now', '-1 month')")
    void deleteOldEntries();


    // ✅ মাসিক ডাটা ফেরত পাওয়া
    @Query("SELECT * FROM monthly_entries ORDER BY date ASC")
    LiveData<List<MonthlyEntryC>> getAllMonthlyEntries();





}
