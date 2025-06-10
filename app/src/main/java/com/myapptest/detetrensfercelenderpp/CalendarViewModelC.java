package com.myapptest.detetrensfercelenderpp;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;

public class CalendarViewModelC extends AndroidViewModel {
    private final CalendarRepositoryC repository;
    private final LiveData<List<CalendarEntryC>> allCalendarData;
    private LiveData<List<MonthlyEntryC>> allMonthlyData;

    public CalendarViewModelC(Application application) {
        super(application);
        repository = new CalendarRepositoryC(application);
        allCalendarData = repository.getAllDailyData();
        allMonthlyData = repository.getAllMonthlyData();
    }

    public LiveData<List<CalendarEntryC>> getAllCalendarEntries() {
        return allCalendarData;
    }

    public LiveData<List<MonthlyEntryC>> getAllMonthlyDataEntries() {
        return allMonthlyData;
    }


    public void insertDEly(CalendarEntryC entry) {
        repository.insertDely(entry);
    }

    public void update(CalendarEntryC entry) {
        repository.update(entry);
    }

    public void delete(CalendarEntryC entry) {
        repository.delete(entry);
    }

    public LiveData<CalendarEntryC> getEntryByDate(String date) {
        return repository.getEntryByDate(date);
    }


    public void insertMonthlyEntries(List<CalendarEntryC> entries) {
        repository.insertMonthlyEntries(entries); // ✅ `MonthlyEntryC` পাঠানো হচ্ছে
    }


    // একমাস পুরোনো ডাটা মুছে ফেলা
    public void deleteOldEntries() {
        repository.deleteOldEntries(); // ✅ পুরাতন ডাটা মুছে ফেলা হচ্ছে
    }


    public List<CalendarEntryC> getOldEntries() {
        return repository.getOldEntriesFromDatabase(); // ✅ পুরাতন `CalendarEntryC` ফেরত দিচ্ছে

    }


/*
    // MonthlyFragment-এর সমস্ত ডাটা দেখানো
    public LiveData<List<MonthlyEntryC>> getAllMonthlyEntries() {
        return repository.getAllMonthlyEntries();
    }

*/


    // Monthly insert
    public void insertMonthly(MonthlyEntryC monthlydata) {
        repository.insertMonthly(monthlydata);
    }

    public void clearDailyData() {
        repository.clearAllDelyEntries();
    }

    // এই মেথডটি আজকের ডেটার ডেটা ফেরত দেবে
    public LiveData<List<CalendarEntryC>> getTodayData(String date) {
        return repository.getTodayData(date);

    }

    public LiveData<List<MonthlyEntryC>> getMonthlyData(String month) {
        return repository.getMonthlyData(month);
    }


    // Check month end and move data
        public void checkMonthEndAndMoveData () {
            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_MONTH);
             calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            int lastDay = calendar.get(Calendar.DAY_OF_MONTH);

            if (today == lastDay) {
                // মাসের শেষ দিন হলে DailyData → MonthlyData এ মুভ করে Clear
                List<CalendarEntryC> currentDailyList = allCalendarData.getValue();
                if (currentDailyList != null && !currentDailyList.isEmpty()) {
                    for (CalendarEntryC data : currentDailyList) {

                        MonthlyEntryC monthlyData = new MonthlyEntryC(data.getDate(), data.getRadioButtonColor(), data.getSpinnerValue(), data.getVelueOtLvEbsentC(), data.getTiffinBillC(), data.getNightBillC());
                        insertMonthly(monthlyData);
                    }
                    clearDailyData();
                }
            }

        }


    }



















