package com.myapptest.detetrensfercelenderpp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
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
        calendarView = view.findViewById(R.id.celend_calendar_View);
        radioGroup = view.findViewById(R.id.calend_radioGroup);
        spinner = view.findViewById(R.id.calend_spinner_cened);
        editText1 = view.findViewById(R.id.calend_editText1);
        editText2 = view.findViewById(R.id.calend_editText2);
        editText3 = view.findViewById(R.id.calend_editText3);
        saveButton = view.findViewById(R.id.calend_btnSave);
        showDeteBtn = view.findViewById(R.id.calend_ShowDete);
        showmonth = view.findViewById(R.id.calend_Showmonth);


        String[] city_list = new String[3];
        city_list[0] = "Delhi";
        city_list[1] = "Gurgaon";
        city_list[2] = "Noida";

        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, city_list);

        spinner.setAdapter(aa);


        showDeteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(v);

                navController.navigate(R.id.action_calendarFragment_to_historyFragment);
            }
        });
        showmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);

                navController.navigate(R.id.action_calendarFragment_to_monthlyFragment);

            }
        });


        // ক্যালেন্ডার থেকে তারিখ সিলেক্ট করা
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.getDate());

            // চেক করা হচ্ছে তারিখে আগের কোনো ডাটা আছে কিনা
            viewModel.getEntryByDate(selectedDate).observe(getViewLifecycleOwner(), entry -> {

                if (entry != null) {

                    editText1.setText(entry.getEditText1());
                    editText2.setText(entry.getEditText2());
                    editText3.setText(entry.getEditText3());
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

    private void saveEntry() {
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
        String text1 = editText1.getText().toString();
        String text2 = editText2.getText().toString();
        String text3 = editText3.getText().toString();

        // চেক করা হচ্ছে একই তারিখে আগের ডাটা আছে কিনা
        viewModel.getEntryByDate(selectedDate).observe(getViewLifecycleOwner(), new Observer<CalendarEntryC>() {
            @Override
            public void onChanged(CalendarEntryC existingEntry) {
                if (existingEntry == null) {
                    // নতুন এন্ট্রি ইনসার্ট
                    CalendarEntryC entry = new CalendarEntryC(selectedDate, color, spinnerValue, text1, text2, text3);
                    viewModel.insert(entry);
                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(selectedDate.substring(0, 4)),
                            Integer.parseInt(selectedDate.substring(5, 7)) - 1,
                            Integer.parseInt(selectedDate.substring(8, 10))), true);
                } else {
                    // আগের ডাটা আপডেট
                    existingEntry = new CalendarEntryC(selectedDate, color, spinnerValue, text1, text2, text3);
                    existingEntry.setId(existingEntry.getId()); // ID অপরিবর্তিত রাখতে হবে
                    viewModel.update(existingEntry);
                }
            }
        });

        Toast.makeText(getContext(), "ডাটা সংরক্ষণ সম্পন্ন", Toast.LENGTH_SHORT).show();
    }
}
