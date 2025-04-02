package com.myapptest.detetrensfercelenderpp;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CalendarViewModelC extends AndroidViewModel {
    private final CalendarRepositoryC repository;
    private final LiveData<List<CalendarEntryC>> allEntries;

    public CalendarViewModelC(Application application) {
        super(application);
        repository = new CalendarRepositoryC(application);
        allEntries = repository.getAllEntries();
    }

    public LiveData<List<CalendarEntryC>> getAllEntries() {
        return allEntries;
    }

    public void insert(CalendarEntryC entry) {
        repository.insert(entry);
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

    public void clearAllEntries() {
        repository.clearAllEntries();
    }


    // New Methods




    // MonthlyFragment-এ ডাটা ইনসার্ট করা
 /*  public void insertMonthlyEntries(List<CalendarEntryC> entries) {
        repository.insertMonthlyEntries(entries);
    }
*/
    public void insertMonthlyEntries(List<CalendarEntryC> entries) {
        repository.insertMonthlyEntries(entries); // ✅ `MonthlyEntryC` পাঠানো হচ্ছে
    }


    // MonthlyFragment-এ ডাটা ইনসার্ট করা
 /*   public void insertMonthlyEntries(List<MonthlyEntryC> mEntries) {
        repository.insertMonthlyEntries(mEntries);
    }*/
       /* // একমাস পুরোনো ডাটা মুছে ফেলা
        public void deleteOldEntries () {
            repository.deleteOldEntries();
        }*/

    // একমাস পুরোনো ডাটা মুছে ফেলা
    public void deleteOldEntries() {
        repository.deleteOldEntries(); // ✅ পুরাতন ডাটা মুছে ফেলা হচ্ছে
    }


    /*public List<CalendarEntryC> getOldEntries() {
        return repository.getOldEntries(); // ✅ পুরাতন `CalendarEntryC` ফেরত দিচ্ছে
    }*/

    // একমাস পুরোনো ডাটা বের করা
/*
    public List<CalendarEntryC> getOldEntries() {
        return repository.getOldEntries();
    }
*/



    public List<CalendarEntryC> getOldEntries() {
        return repository.getOldEntriesFromDatabase(); // ✅ পুরাতন `CalendarEntryC` ফেরত দিচ্ছে

    }
       // MonthlyFragment-এর সমস্ত ডাটা দেখানো
        public LiveData<List<CalendarEntryC>> getAllMonthlyEntries () {
            return repository.getAllMonthlyEntries();
        }


  /*  public LiveData<List<MonthlyEntryC>> getAllMonthlyEntries() {
        return repository.getAllMonthlyEntries(); // ✅ `MonthlyEntryC` ফেরত দিচ্ছে
    }
*/


 /*   public List<CalendarEntryC> getOldEntries() {
        return repository.getOldEntries(); // ✅ পুরাতন `CalendarEntryC` ফেরত দিচ্ছে
    }*/













}


