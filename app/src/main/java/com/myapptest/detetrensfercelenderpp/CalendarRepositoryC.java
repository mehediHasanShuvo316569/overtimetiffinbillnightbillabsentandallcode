package com.myapptest.detetrensfercelenderpp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalendarRepositoryC {
    // private final CalendarDaoC calendarDao;
    //private final ExecutorService executorService;
    private final ExecutorService executorService;
    private final CalendarDaoC calendarDaoC;

    public CalendarRepositoryC(Application application) {
        CalendarDatabaseC db = CalendarDatabaseC.getDatabase(application);
        calendarDaoC = db.calendarDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(CalendarEntryC entry) {
        executorService.execute(() -> calendarDaoC.insert(entry));
    }

    public void update(CalendarEntryC entry) {
        executorService.execute(() -> calendarDaoC.update(entry));
    }

    public void delete(CalendarEntryC entry) {
        executorService.execute(() -> calendarDaoC.delete(entry));
    }

    public LiveData<List<CalendarEntryC>> getAllEntries() {
        return calendarDaoC.getAllEntries();
    }

    public LiveData<CalendarEntryC> getEntryByDate(String date) {
        return calendarDaoC.getEntryByDate(date);
    }

    public void clearAllEntries() {
        executorService.execute(() -> calendarDaoC.clearAllEntries());
    }


    //New Methods //////////;//////////////


    // MonthlyFragment-এ ডাটা ইনসার্ট করা
/*    public void insertMonthlyEntries(List<CalendarEntryC> entries) {
        executorService.execute(() -> calendarDaoC.insertMonthlyEntries(entries));
    }*/

    // MonthlyFragment-এ ডাটা ইনসার্ট করা
    public void insertMonthlyEntries(List<MonthlyEntryC> mEntries) {
        executorService.execute(() -> calendarDaoC.insertMonthlyEntries(mEntries));
    }

    // একমাস পুরোনো ডাটা মুছে ফেলা
    public void deleteOldEntries() {
        executorService.execute(() -> calendarDaoC.deleteOldEntries());
    }


    public List<CalendarEntryC> getOldEntriesFromDatabase() {
        final List<CalendarEntryC>[] oldEntries = new List[1];

        executorService.execute(() -> {
            oldEntries[0] = calendarDaoC.getOldEntries(); // ✅ ব্যাকগ্রাউন্ড থ্রেডে ডাটা ফেচ করা হচ্ছে
        });

        return oldEntries[0];
    }
    // একমাস পুরোনো ডাটা বের করা
 /*   public List<CalendarEntryC> getOldEntries() {
        return calendarDaoC.getOldEntries();
    }
*/


    // MonthlyFragment-এর সমস্ত ডাটা দেখানো privus
/*
    public LiveData<List<CalendarEntryC>> getAllMonthlyEntries() {
        return calendarDaoC.getAllMonthlyEntries();
    }
*/

    public LiveData<List<MonthlyEntryC>> getAllMonthlyEntries() {
        return calendarDaoC.getAllMonthlyEntries(); // ✅ `MonthlyEntryC` রিটার্ন করুন
    }



}
