package com.myapptest.detetrensfercelenderpp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "monthly_entries")
public class MonthlyEntryC {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private int radioButtonColor;
    private String spinnerValue;
    private double velueOtLvEbsent;
    private int tiffinBill;
    private int nightBill;

    public MonthlyEntryC(String date, int radioButtonColor, String spinnerValue, double velueOtLvEbsent, int tiffinBill, int nightBill) {
        this.date = date;
        this.radioButtonColor = radioButtonColor;
        this.spinnerValue = spinnerValue;
        this.velueOtLvEbsent = velueOtLvEbsent;
        this.tiffinBill = tiffinBill;
        this.nightBill = nightBill;
    }

    public MonthlyEntryC() {
        //Emty Constructor
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public int getRadioButtonColor() { return radioButtonColor; }
    public String getSpinnerValue() { return spinnerValue; }
    public double getVelueOtLvEbsent() { return velueOtLvEbsent; }
    public int getTiffinBill() { return tiffinBill; }
    public int getNightBill() { return nightBill; }

    public void setId(int id) { this.id = id; }
    public void setDate(String date) { this.date = date; }
    public void setRadioButtonColor(int radioButtonColor) { this.radioButtonColor = radioButtonColor; }
    public void setSpinnerValue(String spinnerValue) { this.spinnerValue = spinnerValue; }
    public void setVelueOtLvEbsent(double velueOtLvEbsent) { this.velueOtLvEbsent = velueOtLvEbsent; }
    public void setTiffinBill(int tiffinBill) { this.tiffinBill = tiffinBill; }
    public void setNightBill(int nightBill) { this.nightBill = nightBill; }
}
