package com.myapptest.detetrensfercelenderpp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class UpdateDialogC {
    private final AlertDialog dialog;

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

        editText1.setText(entry.getEditText1());
        editText2.setText(entry.getEditText2());
        editText3.setText(entry.getEditText3());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();

        updateButton.setOnClickListener(v -> {
            entry.setEditText1(editText1.getText().toString());
            entry.setEditText2(editText2.getText().toString());
            entry.setEditText3(editText3.getText().toString());
            listener.onUpdate(entry);
            dialog.dismiss();
        });
    }

    public void show() {
        dialog.show();
    }
}
