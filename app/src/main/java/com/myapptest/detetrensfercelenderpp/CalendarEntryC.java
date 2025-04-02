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
    private String editText1;
    private String editText2;
    private String editText3;

    public CalendarEntryC(String date, int radioButtonColor, String spinnerValue, String editText1, String editText2, String editText3) {
        this.date = date;
        this.radioButtonColor = radioButtonColor;
        this.spinnerValue = spinnerValue;
        this.editText1 = editText1;
        this.editText2 = editText2;
        this.editText3 = editText3;
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

    public void setEditText1(String editText1) {
        this.editText1 = editText1;
    }

    public void setEditText2(String editText2) {
        this.editText2 = editText2;
    }

    public void setEditText3(String editText3) {
        this.editText3 = editText3;
    }


    //getters////////////////////////

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDate() { return date; }
    public int getRadioButtonColor() { return radioButtonColor; }
    public String getSpinnerValue() { return spinnerValue; }
    public String getEditText1() { return editText1; }
    public String getEditText2() { return editText2; }
    public String getEditText3() { return editText3; }
}
