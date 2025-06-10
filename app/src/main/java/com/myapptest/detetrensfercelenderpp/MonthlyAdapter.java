package com.myapptest.detetrensfercelenderpp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
public class MonthlyAdapter extends RecyclerView.Adapter<MonthlyAdapter.ViewHolder> {
    private List<MonthlyEntryC> entries;

    public void setEntries(List<MonthlyEntryC> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monthleyt_c, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonthlyEntryC entry = entries.get(position);
        holder.dateTextView.setText(entry.getDate());
        holder.velueTextView.setText(entry.getEditText1());
        holder.typeTextView.setText(entry.getSpinnerValue());
        holder.tiffinBillTextView.setText(entry.getEditText3());
        holder.nightBillTextView.setText(entry.getEditText2());



        //  holder.detailsTextView.setText(entry.getEditText1() + ", " + entry.getEditText2() + ", " + entry.getEditText3());
        // holder.itemView.setBackgroundColor(entry.getColor());
        holder.itemView.setBackgroundColor(entry.getRadioButtonColor());
    }

    @Override
    public int getItemCount() {
        return (entries != null) ? entries.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, velueTextView, typeTextView, tiffinBillTextView, nightBillTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.monthley_date_TV);
            velueTextView = itemView.findViewById(R.id.monthley_velue_TV);
            typeTextView = itemView.findViewById(R.id.monthley_type_TV);
            tiffinBillTextView = itemView.findViewById(R.id.monthley_tiffinBill_TV);
            nightBillTextView = itemView.findViewById(R.id.monthley_nightBill_TV);
        }
    }
}*/


public class MonthlyAdapter extends RecyclerView.Adapter<MonthlyAdapter.ViewHolder> {

    private List<MonthlyEntryC> entries = new ArrayList<>();

    // ডেটা সেট করার জন্য পাবলিক মেথড
    @SuppressLint("NotifyDataSetChanged")
    public void setEntries(List<MonthlyEntryC> entries) {
        if (entries != null) {
            this.entries = entries;
            notifyDataSetChanged();// Simple and works fine
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_monthleyt_c, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonthlyEntryC entry = entries.get(position);

        holder.dateTextView.setText(getSafeText(entry.getDate()));
        // ব্যাকগ্রাউন্ড কালার সেট করুন (রেডিও বাটনের কালার)
        holder.itemView.setBackgroundColor(entry.getRadioButtonColor());
        holder.shiftTextView.setText(getSafeText(entry.getSpinnerValue()));

        holder.valueTextView.setText(getSafeText(String.valueOf(entry.getVelueOtLvEbsent())));
        holder.tiffinBillTextView.setText(getSafeText(String.valueOf(entry.getTiffinBill())));
        holder.nightBillTextView.setText(getSafeText(String.valueOf(entry.getNightBill())));

    }

    @Override
    public int getItemCount() {
        return entries != null ? entries.size() : 0;
    }

    // Null-safe text set করার জন্য হেল্পার মেথড
    private String getSafeText(String text) {
        return text != null ? text : "";
    }

    // ViewHolder ক্লাস
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, valueTextView, shiftTextView, tiffinBillTextView, nightBillTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.monthley_date_TV);
            valueTextView = itemView.findViewById(R.id.monthley_velueOtLvOther_TV);
            shiftTextView = itemView.findViewById(R.id.monthley_Shift_TV);
            tiffinBillTextView = itemView.findViewById(R.id.monthley_tiffinBill_TV);
            nightBillTextView = itemView.findViewById(R.id.monthley_nightBill_TV);
        }
    }
}

