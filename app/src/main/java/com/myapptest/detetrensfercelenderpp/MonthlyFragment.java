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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonthlyFragment extends Fragment {
    private CalendarViewModelC viewModel;
    private RecyclerView recyclerView;
    private MonthlyAdapter adapter;
    private ExecutorService executorService;
    TextView countingItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly, container, false);

        viewModel = new ViewModelProvider(this).get(CalendarViewModelC.class);
        recyclerView = view.findViewById(R.id.recycerel_viewMonthley);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        countingItem = view.findViewById(R.id.counntingMonthItem);

        adapter = new MonthlyAdapter();

        recyclerView.setAdapter(adapter);

        executorService = Executors.newSingleThreadExecutor();

        //moveOldDataToMonthly();


        viewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModelC.class);


      viewModel.getAllMonthlyDataEntries().observe(getViewLifecycleOwner(), new Observer<List<MonthlyEntryC>>() {
          @Override
          public void onChanged(List<MonthlyEntryC> monthlyEntryCS) {
              adapter.setEntries(monthlyEntryCS);
              countingItem.setText(String.valueOf( adapter.getItemCount()));
          }
      });

        return view;
    }



}