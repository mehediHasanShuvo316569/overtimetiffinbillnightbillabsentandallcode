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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonthlyFragment extends Fragment {
    private CalendarViewModelC viewModel;
    private RecyclerView recyclerView;
    private MonthlyAdapter adapter;
    private ExecutorService executorService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly, container, false);

        viewModel = new ViewModelProvider(this).get(CalendarViewModelC.class);
        recyclerView = view.findViewById(R.id.recycerel_viewMonthley);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MonthlyAdapter();

        recyclerView.setAdapter(adapter);

        executorService = Executors.newSingleThreadExecutor();

        moveOldDataToMonthly();

        return view;
    }

    //First Time/////////////////////////////////////////
/*
    private void moveOldDataToMonthly() {
        executorService.execute(() -> {
            List<CalendarEntryC> oldEntries = viewModel.getOldEntries(); // একমাস পুরোনো ডাটা বের করা
            if (!oldEntries.isEmpty()) {
                viewModel.insertMonthlyEntries(oldEntries); // MonthlyFragment-এ ইনসার্ট করা
                viewModel.deleteOldEntries(); // HistoryFragment থেকে ডাটা মুছে ফেলা

            }
        });

        viewModel.getAllMonthlyEntries().observe(getViewLifecycleOwner(), entries -> adapter.setEntries(entries));
    }
*/



 /*   private void moveOldDataToMonthly() {
        executorService.execute(() -> {
            List<CalendarEntryC> oldEntries = viewModel.getOldEntries(); // ✅ একমাস পুরনো ডাটা বের করা

            if (!oldEntries.isEmpty()) {
                List<MonthlyEntryC> monthlyEntries = new ArrayList<>();

                for (CalendarEntryC entry : oldEntries) {
                    monthlyEntries.add(new MonthlyEntryC(
                            entry.getDate(),
                            entry.getRadioButtonColor(),
                            entry.getSpinnerValue(),
                            entry.getEditText1(),
                            entry.getEditText2(),
                            entry.getEditText3()
                    ));
                }

                viewModel.insertMonthlyEntries(monthlyEntries); // ✅ `MonthlyEntryC` হিসাবে ইনসার্ট করুন
                viewModel.deleteOldEntries(); // ✅ পুরানো ডাটা মুছুন
            }
        });

        viewModel.getAllMonthlyEntries().observe(getViewLifecycleOwner(), new Observer<List<MonthlyEntryC>>() {
            @Override
            public void onChanged(List<MonthlyEntryC> entries) {
                adapter.setEntries(entries);
            }
        });



    }

*/


    private void moveOldDataToMonthly() {
        executorService.execute(() -> {
            List<CalendarEntryC> oldEntries = viewModel.getOldEntries(); // ✅ ব্যাকগ্রাউন্ড থ্রেডে `getOldEntries()`

            if (oldEntries != null && !oldEntries.isEmpty()) {
                List<CalendarEntryC> monthlyEntries = new ArrayList<>();

                for (CalendarEntryC entry : oldEntries) {
                    monthlyEntries.add(new CalendarEntryC(
                            entry.getDate(),
                            entry.getRadioButtonColor(),
                            entry.getSpinnerValue(),
                            entry.getEditText1(),
                            entry.getEditText2(),
                            entry.getEditText3()
                    ));
                }

                viewModel.insertMonthlyEntries(monthlyEntries); // ✅ `MonthlyEntryC` হিসাবে ইনসার্ট করুন
                viewModel.deleteOldEntries(); // ✅ পুরাতন ডাটা মুছে ফেলা
            }
        });

        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
/*
               viewModel.getAllMonthlyEntries().observe(MonthlyFragment.this.getViewLifecycleOwner(), new Observer<List<CalendarEntryC>>() {
                    @Override
                    public void onChanged(List<CalendarEntryC> entries) {
                     //  adapter.setEntries(entries);
                       if (entries != null) {
                           adapter.setEntries(entries);
                       }
                  }
                });
*/

/*
              viewModel.getAllMonthlyEntries().observe(requireActivity(), new Observer<List<CalendarEntryC>>() {
                  @Override
                  public void onChanged(List<CalendarEntryC> monthlyEntryCS) {

                      adapter.setEntries(monthlyEntryCS);

                  }
              });
*/

                viewModel.getAllMonthlyEntries().observe(requireActivity(), new Observer<List<CalendarEntryC>>() {
                    @Override
                    public void onChanged(List<CalendarEntryC> calendarEntryCS) {

                        adapter.setEntries(calendarEntryCS);

                    }
                });

            }
        });
    }

    private class MonthlyAdapter extends RecyclerView.Adapter<MonthlyAdapter.ViewHolder> {
        private List<CalendarEntryC> entries;
        // private List<MonthlyEntryC> entries;


        public void setEntries(List<CalendarEntryC> entries) {
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
            CalendarEntryC entry = entries.get(position);
            holder.dateTextView.setText(entry.getDate());
            holder.velueTextView.setText(entry.getEditText1());
            holder.typeTextView.setText(entry.getEditText2());
            holder.tiffinBillTextView.setText(entry.getEditText3());

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
                dateTextView = itemView.findViewById(R.id.date_TV);
                velueTextView = itemView.findViewById(R.id.velue_TV);
                typeTextView = itemView.findViewById(R.id.type_TV);
                tiffinBillTextView = itemView.findViewById(R.id.tiffinBill_TV);
                nightBillTextView = itemView.findViewById(R.id.type_TV);
            }
        }
    }

}