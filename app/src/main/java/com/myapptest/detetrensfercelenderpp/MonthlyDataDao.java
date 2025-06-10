package com.myapptest.detetrensfercelenderpp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MonthlyDataDao {

    @Insert
    void insert(MonthlyEntryC data);

    @Query("SELECT * FROM monthly_entries ORDER BY id DESC")
    LiveData<List<MonthlyEntryC>> getAllMonthlyData();

    @Query("SELECT * FROM monthly_entries WHERE date LIKE :month || '%'")
    LiveData<List<MonthlyEntryC>> getDataByMonth(String month);


}
