package com.myapptest.detetrensfercelenderpp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CalendarFragmentC extends Fragment {
    private CalendarViewModelC viewModel;
    private MaterialCalendarView calendarView;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private EditText editText1, editText2, editText3;
    private Button saveButton, showDeteBtn, showmonth;
    private String selectedDate = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_fragmen_c, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModelC.class);


        itial(view);


        String[] city_list = new String[3];
        city_list[0] = "Delhi";
        city_list[1] = "Gurgaon";
        city_list[2] = "Noida";

        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, city_list);

        spinner.setAdapter(aa);


        showDeteBtn.setOnClickListener(v -> {

            NavController navController = Navigation.findNavController(v);

            navController.navigate(R.id.action_calendarFragment_to_historyFragment);
        });

        showmonth.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);

            navController.navigate(R.id.action_calendarFragment_to_monthlyFragment);

        });


        // ক্যালেন্ডার থেকে তারিখ সিলেক্ট করা
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.getDate());

            // চেক করা হচ্ছে তারিখে আগের কোনো ডাটা আছে কিনা
            viewModel.getEntryByDate(selectedDate).observe(getViewLifecycleOwner(), entry -> {

                if (entry != null) {

                    radioGroup.setId(entry.getRadioButtonColor());
                    editText1.setText(String.valueOf(entry.getVelueOtLvEbsentC()));
                    editText2.setText(String.valueOf(entry.getTiffinBillC()));
                    editText3.setText(String.valueOf(entry.getNightBillC()));
                } else {
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                }
            });
        });

        // Save Button ক্লিক ইভেন্ট
        saveButton.setOnClickListener(v -> saveEntry());

        return view;
    }

    private void itial(View view) {
        calendarView = view.findViewById(R.id.celend_calendar_View);
        radioGroup = view.findViewById(R.id.calend_radioGroup);
        spinner = view.findViewById(R.id.calend_spinner_cened);
        editText1 = view.findViewById(R.id.calend_editText1);
        editText2 = view.findViewById(R.id.calend_editText2);
        editText3 = view.findViewById(R.id.calend_editText3);
        saveButton = view.findViewById(R.id.calend_btnSave);
        showDeteBtn = view.findViewById(R.id.calend_ShowDete);
        showmonth = view.findViewById(R.id.calend_Showmonth);

    }


//Data Saveing Start/////////////////////
    private void saveEntry() {
        if (!isInputValid()) return;

        int selectedColor = getSelectedRadioColor();
        String spinnerValue = spinner.getSelectedItem().toString();
        double valueOfAbsent = parseDoubleSafe(editText1.getText().toString());
        int tiffinBill = parseIntSafe(editText2.getText().toString());
        int nightBill = parseIntSafe(editText3.getText().toString());

        viewModel.getEntryByDate(selectedDate).removeObservers(getViewLifecycleOwner());
        viewModel.getEntryByDate(selectedDate).observe(getViewLifecycleOwner(), existingEntry -> {
            if (existingEntry == null) {
                insertNewEntry(selectedColor, spinnerValue, valueOfAbsent, tiffinBill, nightBill);
            } else {
                updateExistingEntry(existingEntry.getId(), selectedColor, spinnerValue, valueOfAbsent, tiffinBill, nightBill);
            }
        });

        Toast.makeText(getContext(), "ডাটা সংরক্ষণ সম্পন্ন", Toast.LENGTH_SHORT).show();
    }
    private boolean isInputValid() {
        if (selectedDate == null || selectedDate.isEmpty()) {
            Toast.makeText(getContext(), "একটি তারিখ নির্বাচন করুন", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "একটি রেডিও বাটন নির্বাচন করুন", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editText1.getText().toString().trim().isEmpty() ||
                editText2.getText().toString().trim().isEmpty() ||
                editText3.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "সবগুলো ঘর পূরণ করুন", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private int getSelectedRadioColor() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadio = getView().findViewById(selectedId);
        return selectedRadio.getCurrentTextColor();
    }
    private void insertNewEntry(int color, String spinnerValue, double absent, int tiffin, int night) {
        CalendarEntryC entry = new CalendarEntryC(selectedDate, color, spinnerValue, absent, tiffin, night);
        viewModel.insertDEly(entry);

        // Mark selected date
        markDateSelected();
    }
    private void updateExistingEntry(int id, int color, String spinnerValue, double absent, int tiffin, int night) {
        CalendarEntryC updatedEntry = new CalendarEntryC(selectedDate, color, spinnerValue, absent, tiffin, night);
        updatedEntry.setId(id);
        viewModel.update(updatedEntry);

        markDateSelected();
    }
    private void markDateSelected() {
        try {
            int year = Integer.parseInt(selectedDate.substring(0, 4));
            int month = Integer.parseInt(selectedDate.substring(5, 7)) - 1;
            int day = Integer.parseInt(selectedDate.substring(8, 10));

            calendarView.setDateSelected(CalendarDay.from(year, month, day), true);
        } catch (Exception e) {
            //Log.e("Calendar", "Invalid date format: " + selectedDate);
            Toast.makeText(getContext(), "" + ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private double parseDoubleSafe(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    private int parseIntSafe(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

//Data Saving End Is Here////////////////////////

  /*  private void saveEntry() {
        if (selectedDate.isEmpty()) {
            Toast.makeText(getContext(), "একটি তারিখ নির্বাচন করুন", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            Toast.makeText(getContext(), "একটি রেডিও বাটন নির্বাচন করুন", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = getView().findViewById(selectedRadioButtonId);
        int color = selectedRadioButton.getCurrentTextColor();

        String spinnerValue = spinner.getSelectedItem().toString();
        double velueOtLEbsent = Double.parseDouble(editText1.getText().toString());
        int tiffinBill = Integer.parseInt(editText2.getText().toString());
        int nightBill = Integer.parseInt(editText3.getText().toString());

        // চেক করা হচ্ছে একই তারিখে আগের ডাটা আছে কিনা
        viewModel.getEntryByDate(selectedDate).observe(getViewLifecycleOwner(), existingEntry -> {
            if (existingEntry == null) {
                // নতুন এন্ট্রি ইনসার্ট
                CalendarEntryC entry = new CalendarEntryC(selectedDate, color, spinnerValue, velueOtLEbsent, tiffinBill, nightBill);
                viewModel.insertDEly(entry);
                calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(selectedDate.substring(0, 4)),
                        Integer.parseInt(selectedDate.substring(5, 7)) - 1,
                        Integer.parseInt(selectedDate.substring(8, 10))), true);
            } else {
                // আগের ডাটা আপডেট
                existingEntry = new CalendarEntryC(selectedDate, color, spinnerValue, velueOtLEbsent, tiffinBill, nightBill);
                existingEntry.setId(existingEntry.getId()); // ID অপরিবর্তিত রাখতে হবে
                viewModel.update(existingEntry);
            }
        });

        Toast.makeText(getContext(), "ডাটা সংরক্ষণ সম্পন্ন", Toast.LENGTH_SHORT).show();
    }*/
}
