package com.myapptest.detetrensfercelenderpp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calendar_entries")
public class CalendarEntryC {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date; // YYYY-MM-DD ফরম্যাটে তারিখ সংরক্ষণ
    private int radioButtonColor; // রেডিও বাটনের রঙ সংরক্ষণ
    private String spinnerValue;
    private double velueOtLvEbsentC;
    private int tiffinBillC;
    private int nightBillC;

    public CalendarEntryC(String date, int radioButtonColor, String spinnerValue, double velueOtLvEbsentC, int tiffinBillC, int nightBillC) {
        this.date = date;
        this.radioButtonColor = radioButtonColor;
        this.spinnerValue = spinnerValue;
        this.velueOtLvEbsentC = velueOtLvEbsentC;
        this.tiffinBillC = tiffinBillC;
        this.nightBillC = nightBillC;
    }

    public CalendarEntryC() {
        //Emty Constructor
    }
//setters/////////////////////////////////////////////

    public void setDate(String date) {
        this.date = date;
    }

    public void setRadioButtonColor(int radioButtonColor) {
        this.radioButtonColor = radioButtonColor;
    }

    public void setSpinnerValue(String spinnerValue) {
        this.spinnerValue = spinnerValue;
    }

    public void setVelueOtLvEbsentC(double velueOtLvEbsentC) {
        this.velueOtLvEbsentC = velueOtLvEbsentC;
    }

    public void setTiffinBillC(int tiffinBillC) {
        this.tiffinBillC = tiffinBillC;
    }

    public void setNightBillC(int nightBillC) {
        this.nightBillC = nightBillC;
    }


    //getters////////////////////////

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDate() { return date; }
    public int getRadioButtonColor() { return radioButtonColor; }
    public String getSpinnerValue() { return spinnerValue; }
    public double getVelueOtLvEbsentC() { return velueOtLvEbsentC; }
    public int getTiffinBillC() { return tiffinBillC; }
    public int getNightBillC() { return nightBillC; }
}
