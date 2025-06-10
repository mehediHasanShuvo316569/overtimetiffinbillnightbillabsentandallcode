package com.myapptest.detetrensfercelenderpp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;/*
public class UpdateDialogC {
    private final AlertDialog dialog;
    private OnUpdateListener listener;

    public interface OnUpdateListener {
        void onUpdate(CalendarEntryC updatedEntry);
    }

    public UpdateDialogC(Context context, CalendarEntryC entry, OnUpdateListener listener) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_c, null);

        EditText editText1 = view.findViewById(R.id.celender_dialog_editText1);
        EditText editText2 = view.findViewById(R.id.celender_dialog_editText2);
        EditText editText3 = view.findViewById(R.id.celender_dialog_editText3);
        Spinner dSpinner = view.findViewById(R.id.celender_dialog_spinner);
        RadioGroup dRadioGroup = view.findViewById(R.id.celender_dialog_radioGroup);
        Button updateButton = view.findViewById(R.id.celender_dialog_button);

        // Set existing values to EditTexts
        editText1.setText(entry.getEditText1());
        editText2.setText(entry.getEditText2());
        editText3.setText(entry.getEditText3());

        // Setup Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dSpinner.setAdapter(adapter);

        // Set Spinner previously selected item
        if (entry.getSpinnerValue() != null) {
            int spinnerPosition = adapter.getPosition(entry.getSpinnerValue());
            dSpinner.setSelection(spinnerPosition);
        }

        // Set previously selected RadioButton
        for (int i = 0; i < dRadioGroup.getChildCount(); i++) {
            View child = dRadioGroup.getChildAt(i);
            if (child instanceof RadioButton) {
                RadioButton rb = (RadioButton) child;
                if (rb.getCurrentTextColor() == entry.getRadioButtonColor()) {
                    rb.setChecked(true);
                    break;
                }
            }
        }

        // Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();

        // Update Button Action
        updateButton.setOnClickListener(v -> {
            // Update entry fields
            entry.setEditText1(editText1.getText().toString());
            entry.setEditText2(editText2.getText().toString());
            entry.setEditText3(editText3.getText().toString());

            if (dSpinner.getSelectedItem() != null) {
                entry.setSpinnerValue(dSpinner.getSelectedItem().toString());
            }

            int selectedRadioButtonId = dRadioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId != -1) {
                RadioButton selectedRadioButton = view.findViewById(selectedRadioButtonId);
                if (selectedRadioButton != null) {
                    entry.setRadioButtonColor(selectedRadioButton.getCurrentTextColor());
                }
            }

            // Callback with updated entry
            if (listener != null) {
                listener.onUpdate(entry);
            }

            dialog.dismiss();
        });

    }

    public void show() {
        dialog.show();
    }
}
*/

import androidx.annotation.NonNull;

/*
public class UpdateDialogC {
    private final AlertDialog dialog;
    private final Context context;
    private final View view;

    public interface OnUpdateListener {
        void onUpdate(CalendarEntryC updatedEntry);
    }

    public UpdateDialogC(Context context, CalendarEntryC entry, OnUpdateListener listener) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.dialog_update_c, null);

        EditText editText1 = view.findViewById(R.id.celender_dialog_editText1);
        EditText editText2 = view.findViewById(R.id.celender_dialog_editText2);
        EditText editText3 = view.findViewById(R.id.celender_dialog_editText3);
        Spinner dSpinner = view.findViewById(R.id.celender_dialog_spinner);
        RadioGroup dRadioGroup = view.findViewById(R.id.celender_dialog_radioGroup);
        Button updateButton = view.findViewById(R.id.celender_dialog_button);

        // Set existing values
        editText1.setText(entry.getEditText1());
        editText2.setText(entry.getEditText2());
        editText3.setText(entry.getEditText3());

        // Spinner setup
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dSpinner.setAdapter(adapter);

        if (entry.getSpinnerValue() != null) {
            int spinnerPosition = adapter.getPosition(entry.getSpinnerValue());
            dSpinner.setSelection(spinnerPosition);
        }

        // Select the correct RadioButton by color tag
        for (int i = 0; i < dRadioGroup.getChildCount(); i++) {
            View child = dRadioGroup.getChildAt(i);
            if (child instanceof RadioButton) {
                RadioButton rb = (RadioButton) child;
                if (rb.getTag() != null &&
                        rb.getTag().toString().equalsIgnoreCase(
                                String.format("#%06X", (0xFFFFFF & entry.getRadioButtonColor())))) {
                    rb.setChecked(true);
                    break;
                }
            }
        }

        // Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();

        // Update Button Logic
        updateButton.setOnClickListener(v -> {
            // Validation
            if (editText1.getText().toString().trim().isEmpty()) {
                editText1.setError("ফিল্ডটি পূরণ করুন");
                return;
            }

            // Hide Keyboard
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            // Set updated values
            entry.setEditText1(editText1.getText().toString().trim());
            entry.setEditText2(editText2.getText().toString().trim());
            entry.setEditText3(editText3.getText().toString().trim());

            if (dSpinner.getSelectedItem() != null) {
                entry.setSpinnerValue(dSpinner.getSelectedItem().toString());
            }

            int selectedRadioButtonId = dRadioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId != -1) {
                RadioButton selectedRadioButton = view.findViewById(selectedRadioButtonId);
                if (selectedRadioButton != null && selectedRadioButton.getTag() != null) {
                    entry.setRadioButtonColor(Color.parseColor(selectedRadioButton.getTag().toString()));
                }
            }

            // Callback
            if (listener != null) {
                listener.onUpdate(entry);
            }

            dialog.dismiss();
        });
    }

    public void show() {
        dialog.show();
    }
}
*/



public class UpdateDialogC extends Dialog {

    private final CalendarEntryC entry;
    private final OnUpdateListener listener;

    private EditText editText1, editText2, editText3;
    private Spinner spinner;
    private RadioGroup radioGroup;

    public interface OnUpdateListener {
        void onUpdate(CalendarEntryC updatedEntry);
    }

    public UpdateDialogC(@NonNull Context context, CalendarEntryC entry, OnUpdateListener listener) {
        super(context);
        this.entry = entry;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_c);

        // Initialize views
        editText1 = findViewById(R.id.dialog_editText1);
        editText2 = findViewById(R.id.dialog_editText2);
        editText3 = findViewById(R.id.dialog_editText3);
        spinner = findViewById(R.id.dialog_spinner);
        radioGroup = findViewById(R.id.dialog_radioGroup);
        Button updateButton = findViewById(R.id.dialog_update_btn);
        Button cancelButton = findViewById(R.id.dialog_cancel_btn);

        // Populate existing values
        editText1.setText(String.valueOf(entry.getVelueOtLvEbsentC()));
        editText2.setText(String.valueOf(entry.getTiffinBillC()));
        editText3.setText(String.valueOf(entry.getNightBillC()));
        // Spinner selection
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (entry.getSpinnerValue() != null) {
            int position = adapter.getPosition(entry.getSpinnerValue());
            spinner.setSelection(position);
        }

        // Radio color
        int existingColor = entry.getRadioButtonColor();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            if (Color.parseColor((String) rb.getTag()) == existingColor) {
                rb.setChecked(true);
                break;
            }
        }

        updateButton.setOnClickListener(v -> {
            double text1 = Double.parseDouble(editText1.getText().toString().trim());
            int text2 = Integer.parseInt(editText2.getText().toString().trim());
            int text3 = Integer.parseInt(editText3.getText().toString().trim());
            String spinnerValue = spinner.getSelectedItem().toString();
            int selectedColor = Color.GRAY;

            int checkedId = radioGroup.getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton selectedButton = findViewById(checkedId);
                selectedColor = Color.parseColor((String) selectedButton.getTag());
            }

            // Update entry
            entry.setVelueOtLvEbsentC(text1);
            entry.setTiffinBillC(text2);
            entry.setNightBillC(text3);
            entry.setSpinnerValue(spinnerValue);
            entry.setRadioButtonColor(selectedColor);

            // Callback
            if (listener != null) {
                listener.onUpdate(entry);
            }

            dismiss();
        });

        cancelButton.setOnClickListener(v -> dismiss());
    }
}

