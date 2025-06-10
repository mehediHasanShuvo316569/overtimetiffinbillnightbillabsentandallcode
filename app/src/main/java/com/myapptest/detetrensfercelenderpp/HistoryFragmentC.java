package com.myapptest.detetrensfercelenderpp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryFragmentC extends Fragment {
    private CalendarViewModelC viewModel;
    private RecyclerView recyclerView;
    private TextView counterTextView;
    private HistoryAdapter historyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_c, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModelC.class);
        recyclerView = view.findViewById(R.id.history_recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        counterTextView = view.findViewById(R.id.counterTV);



        historyAdapter = new HistoryAdapter(
                requireContext(),
                new ArrayList<>(),
                updatedEntry -> viewModel.update(updatedEntry),
                new HistoryAdapter.OnItemDeleteOrUndoListener() {
                    @Override
                    public void onDelete(CalendarEntryC entry) {
                        viewModel.delete(entry);
                    }

                    @Override
                    public void onUndo(CalendarEntryC entry) {
                        viewModel.insertDEly(entry);
                    }
                }
        );
        recyclerView.setAdapter(historyAdapter);

        viewModel.getAllCalendarEntries().observe(getViewLifecycleOwner(), calendarEntryCS -> {
            historyAdapter.setEntries(calendarEntryCS);
            counterTextView.setText(String.valueOf(calendarEntryCS.size()));
            viewModel.checkMonthEndAndMoveData();
        });












        // historyAdapter = new HistoryAdapter(requireContext());
        //recyclerView.setAdapter(historyAdapter);


/*

        historyAdapter = new HistoryAdapter(
                requireContext(),
                new ArrayList<>(),
                updatedEntry -> viewModel.update(updatedEntry), // Update callback
                entry -> {
                    if (entry != null) {
                        viewModel.delete(entry); // Delete
                    } else {
                        viewModel.insertDEly(entry); // Undo (you can refine this)
                    }
                }
        );
        recyclerView.setAdapter(historyAdapter);




        viewModel.getAllCalendarEntries().observe(getViewLifecycleOwner(), new Observer<List<CalendarEntryC>>() {
            @Override
            public void onChanged(List<CalendarEntryC> calendarEntryCS) {
                historyAdapter.setEntries(calendarEntryCS);
                counterTextView.setText(String.valueOf(calendarEntryCS.size()));
                viewModel.checkMonthEndAndMoveData();
            }
        });

*/

        return view;
    }


}
