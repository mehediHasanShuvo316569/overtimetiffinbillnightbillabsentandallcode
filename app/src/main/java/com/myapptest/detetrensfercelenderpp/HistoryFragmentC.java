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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryFragmentC extends Fragment {
    private CalendarViewModelC viewModel;
    private RecyclerView recyclerView;
    private TextView counterTextView;
    private HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_c, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModelC.class);
        recyclerView = view.findViewById(R.id.history_recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        counterTextView = view.findViewById(R.id.counterTV);

        adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);

        // ডাটাবেজ থেকে ডাটা লোড করা
        viewModel.getAllEntries().observe(getViewLifecycleOwner(), (List<CalendarEntryC> entries) -> {

            adapter.setEntries(entries);
            //counterTextView.setText(String.valueOf(entries.size()));

        });


        return view;
    }

    private class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
        private List<CalendarEntryC> entries;
        CalendarEntryC positionEntitis;

        public void setEntries(List<CalendarEntryC> entries) {
            this.entries = entries;
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_c, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            CalendarEntryC entry = entries.get(position);
            holder.dateTextV.setText(entry.getDate());
          //  holder.velueTextV.setText(entry.getRadioButtonColor());
            holder.velueTextV.setTextColor(entry.getRadioButtonColor());
            holder.typeTextV.setText(entry.getSpinnerValue());
            holder.tiffinBillTextV.setText(entry.getEditText2());
            holder.nightBillTextV.setText(entry.getEditText3());
           // holder.velueTextV.setText(entry.getEditText1() + ", " + entry.getEditText2() + ", " + entry.getEditText3());
           // holder.itemView.setBackgroundColor(entry.getColor);
            holder.itemView.setBackgroundColor(entry.getRadioButtonColor());

            holder.itemView.setOnLongClickListener(v -> {
                showOptionsDialog(entry);
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return (entries != null) ? entries.size() : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView dateTextV, velueTextV,typeTextV,tiffinBillTextV,nightBillTextV;

            ViewHolder(View itemView) {
                super(itemView);
                dateTextV = itemView.findViewById(R.id.history_date_pic);
                velueTextV = itemView.findViewById(R.id.history_velue_TV);
                typeTextV = itemView.findViewById(R.id.history_type_TV);
                tiffinBillTextV = itemView.findViewById(R.id.history_tiffinBill_TV);
                nightBillTextV = itemView.findViewById(R.id.history_nightBill_TV);
            }
        }
    }

    private void showOptionsDialog(CalendarEntryC entry) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("নির্বাচন করুন")
                .setItems(new CharSequence[]{"আপডেট", "ডিলিট"}, (dialog, which) -> {
                    if (which == 0) {
                        showUpdateDialog(entry);
                    } else {
                        viewModel.delete(entry);
                        Toast.makeText(getContext(), "ডাটা ডিলেট করা হয়েছে", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showUpdateDialog(CalendarEntryC entry) {
        UpdateDialogC updateDialog = new UpdateDialogC(getContext(), entry, updatedEntry -> {
            viewModel.update(updatedEntry);
            Toast.makeText(getContext(), "ডাটা আপডেট করা হয়েছে", Toast.LENGTH_SHORT).show();
        });
        updateDialog.show();
    }
}
