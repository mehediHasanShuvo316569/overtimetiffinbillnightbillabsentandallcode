package com.myapptest.detetrensfercelenderpp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalendarRepositoryC {
    private final ExecutorService executorService;
    private final CalendarDaoC calendarDaoC;
    private final MonthlyDataDao monthlyDataDao;
    private final LiveData<List<CalendarEntryC>> allcalendarData;
    private final LiveData<List<MonthlyEntryC>> allMonthlyData;

    public CalendarRepositoryC(Application application) {
        CalendarDatabaseC db = CalendarDatabaseC.getDatabase(application);
        calendarDaoC = db.calendarDao();
        monthlyDataDao = db.monthlyDataDao();
        allcalendarData = calendarDaoC.getAllDailyData();
        allMonthlyData = monthlyDataDao.getAllMonthlyData();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insertDely(CalendarEntryC entry) {
        executorService.execute(() -> calendarDaoC.insert(entry));
    }

    public void update(CalendarEntryC entry) {
        executorService.execute(() -> calendarDaoC.update(entry));
    }

    public void delete(CalendarEntryC entry) {
        executorService.execute(() -> calendarDaoC.delete(entry));
    }

    public LiveData<List<CalendarEntryC>> getAllEntries() {
        return calendarDaoC.getAllDailyData();
    }

    public LiveData<CalendarEntryC> getEntryByDate(String date) {
        return calendarDaoC.getEntryByDate(date);
    }


    // Clear all daily data
    public void clearAllDelyEntries() {
        executorService.execute(calendarDaoC::clearAllDelyEntries);
    }



    //New Methods //////////;//////////////


    // MonthlyFragment-এ ডাটা ইনসার্ট করা
    public void insertMonthlyEntries(List<CalendarEntryC> mEntries) {
        executorService.execute(() -> calendarDaoC.insertMonthlyEntries(mEntries));
    }

    // একমাস পুরোনো ডাটা মুছে ফেলা
    public void deleteOldEntries() {
        executorService.execute(calendarDaoC::deleteOldEntries);
    }


    public List<CalendarEntryC> getOldEntriesFromDatabase() {
        final List<CalendarEntryC>[] oldEntries = new List[1];

        executorService.execute(() -> {
            oldEntries[0] = calendarDaoC.getOldEntries(); // ✅ ব্যাকগ্রাউন্ড থ্রেডে ডাটা ফেচ করা হচ্ছে
        });

        return oldEntries[0];
    }


/*
    public LiveData<List<MonthlyEntryC>> getAllMonthlyEntries() {
        return calendarDaoC.getAllMonthlyEntries(); // ✅ `MonthlyEntryC` রিটার্ন করুন
    }

*/


    // Insert monthly data
    public void insertMonthly(MonthlyEntryC data) {
        Executors.newSingleThreadExecutor().execute(() -> monthlyDataDao.insert(data));
    }

    // Get all daily data
    public LiveData<List<CalendarEntryC>> getAllDailyData() {
        return allcalendarData;
    }
    public LiveData<List<CalendarEntryC>> getTodayData(String date) {
        return calendarDaoC.getDataByDate(date);
    }
    public LiveData<List<MonthlyEntryC>> getMonthlyData(String month) {
        return monthlyDataDao.getDataByMonth(month); // তুমি যে DAO ইউজ করছো
    }



    // Get all monthly data
   public LiveData<List<MonthlyEntryC>> getAllMonthlyData() {
        return allMonthlyData;
    }


}
