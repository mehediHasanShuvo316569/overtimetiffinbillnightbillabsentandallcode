package com.myapptest.detetrensfercelenderpp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
/*

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<CalendarEntryC> entries;
    private CalendarViewModelC viewModelC;
    private Context context;


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
            velueTextV = itemView.findViewById(R.id.history_velue_ot_TV);
            typeTextV = itemView.findViewById(R.id.history_shift_TV);
            tiffinBillTextV = itemView.findViewById(R.id.history_tiffinBill_TV);
            nightBillTextV = itemView.findViewById(R.id.history_nightBill_TV);
        }
    }

    private void showOptionsDialog(CalendarEntryC entry) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
        builder.setTitle("নির্বাচন করুন")
                .setItems(new CharSequence[]{"আপডেট", "ডিলিট"}, (dialog, which) -> {
                    if (which == 0) {
                        showUpdateDialog(entry);
                    } else {
                        viewModelC.delete(entry);
                        Toast.makeText(, "ডাটা ডিলেট করা হয়েছে", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showUpdateDialog(CalendarEntryC entry) {
        UpdateDialogC updateDialog = new UpdateDialogC(context.getApplicationContext(), entry, updatedEntry -> {
            viewModelC.update(updatedEntry);
            Toast.makeText(context.getApplicationContext(), "ডাটা আপডেট করা হয়েছে", Toast.LENGTH_SHORT).show();
        });
        updateDialog.show();
    }

}
*/



public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<CalendarEntryC> entries = new ArrayList<>();
    private final Context context;

    // Callback interfaces
    private final OnItemUpdateListener updateListener;
    private final OnItemDeleteOrUndoListener deleteOrUndoListener;

    // Interfaces
    public interface OnItemUpdateListener {
        void onItemUpdate(CalendarEntryC updatedEntry);
    }

    public interface OnItemDeleteOrUndoListener {
        void onDelete(CalendarEntryC entry);
        void onUndo(CalendarEntryC entry);
    }

    // Constructor
    public HistoryAdapter(Context context,
                          List<CalendarEntryC> entries,
                          OnItemUpdateListener updateListener,
                          OnItemDeleteOrUndoListener deleteOrUndoListener) {
        this.context = context;
        this.entries = entries;
        this.updateListener = updateListener;
        this.deleteOrUndoListener = deleteOrUndoListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setEntries(List<CalendarEntryC> entries) {
        if (entries != null) {
            this.entries = entries;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_c, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalendarEntryC entry = entries.get(position);

        holder.dateTextV.setText(entry.getDate());
       // holder.valueTextV.setTextColor(Integer.parseInt(entry.getEditText1()));
        holder.valueTextV.setText(String.valueOf(entry.getVelueOtLvEbsentC()));
        holder.typeTextV.setText(entry.getSpinnerValue());
        holder.tiffinBillTextV.setText(String.valueOf(entry.getTiffinBillC()));
        holder.nightBillTextV.setText(String.valueOf(entry.getNightBillC()));
        holder.itemView.setBackgroundColor(entry.getRadioButtonColor());

        holder.itemView.setOnLongClickListener(v -> {
            showOptionsDialog(entry, holder.getAdapterPosition());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    private void showOptionsDialog(CalendarEntryC entry, int position) {
        new AlertDialog.Builder(context)
                .setTitle("নির্বাচন করুন")
                .setItems(new CharSequence[]{"আপডেট", "ডিলিট"}, (dialog, which) -> {
                    if (which == 0) {
                        showUpdateDialog(entry);
                    } else {
                        deleteWithUndo(entry, position);
                    }
                })
                .show();
    }

    private void showUpdateDialog(CalendarEntryC entry) {
        UpdateDialogC dialog = new UpdateDialogC(context, entry, updatedEntry -> {
            if (updateListener != null) {
                updateListener.onItemUpdate(updatedEntry);
            }
        });
        dialog.show();
    }

    private void deleteWithUndo(CalendarEntryC entry, int position) {
        entries.remove(position);
        notifyItemRemoved(position);

        if (deleteOrUndoListener != null) {
            deleteOrUndoListener.onDelete(entry);
        }

        Snackbar snackbar = Snackbar.make(
                ((Activity) context).findViewById(android.R.id.content),
                "ডাটা ডিলেট হয়েছে",
                Snackbar.LENGTH_LONG
        );

        snackbar.setAction("আনডু", v -> {
            entries.add(position, entry);
            notifyItemInserted(position);

            if (deleteOrUndoListener != null) {
                deleteOrUndoListener.onUndo(entry);
            }
        });

        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        snackbar.show();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextV, valueTextV, typeTextV, tiffinBillTextV, nightBillTextV;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextV = itemView.findViewById(R.id.history_date_pic);
            valueTextV = itemView.findViewById(R.id.history_velue_ot_TV);
            typeTextV = itemView.findViewById(R.id.history_shift_TV);
            tiffinBillTextV = itemView.findViewById(R.id.history_tiffinBill_TV);
            nightBillTextV = itemView.findViewById(R.id.history_nightBill_TV);
        }
    }
}





















/*
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<CalendarEntryC> entries = new ArrayList<>();
   private CalendarViewModelC viewModelC;
    private final Context context;

    private final OnItemUpdateListener listener;
    private final OnItemDeleteListener deleteListener;

    public interface OnItemUpdateListener {
        void onItemUpdate(CalendarEntryC updatedEntry);
    }
    public interface OnItemDeleteListener {
        void onItemDelete(CalendarEntryC entry);
    }

    *//*public HistoryAdapter(Context context) {
        this.context = context;
        //this.viewModelC = viewModelC;
    }
*//*
 *//*   public HistoryAdapter(Context context ,List<CalendarEntryC> entries, OnItemUpdateListener listener) {
        this.context = context;
        this.entries = entries;
        this.listener = listener;
    }*//*

    public HistoryAdapter(Context context, List<CalendarEntryC> entries, OnItemUpdateListener listener, OnItemDeleteListener deleteListener) {
        this.context = context;
        this.entries = entries;
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setEntries(List<CalendarEntryC> entries) {
        if (entries != null) {
            this.entries = entries;
            notifyDataSetChanged(); // DiffUtil দিয়ে future enhancement করা যাবে
        }
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
        holder.velueTextV.setTextColor(entry.getRadioButtonColor());
        holder.typeTextV.setText(entry.getSpinnerValue());
        holder.tiffinBillTextV.setText(entry.getEditText2());
        holder.nightBillTextV.setText(entry.getEditText3());
        holder.itemView.setBackgroundColor(entry.getRadioButtonColor());

        holder.itemView.setOnLongClickListener(v -> {
            showOptionsDialog(entry);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextV, velueTextV, typeTextV, tiffinBillTextV, nightBillTextV;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextV = itemView.findViewById(R.id.history_date_pic);
            velueTextV = itemView.findViewById(R.id.history_velue_ot_TV);
            typeTextV = itemView.findViewById(R.id.history_shift_TV);
            tiffinBillTextV = itemView.findViewById(R.id.history_tiffinBill_TV);
            nightBillTextV = itemView.findViewById(R.id.history_nightBill_TV);
        }
    }

    private void showOptionsDialog(CalendarEntryC entry) {
        new AlertDialog.Builder(context)
                .setTitle("নির্বাচন করুন")
                .setItems(new CharSequence[]{"আপডেট", "ডিলিট"}, (dialog, which) -> {
                    if (which == 0) {
                        showUpdateDialog(entry);
                    } else {
                        //viewModelC.delete(entry);
                        //Toast.makeText(context, "ডাটা ডিলেট করা হয়েছে", Toast.LENGTH_SHORT).show();
                        deleteWithUndo(entry);
                    }
                })
                .show();
    }

*//*    private void deleteWithUndo(CalendarEntryC entry) {
        int position = entries.indexOf(entry);
        if (position != -1) {
            entries.remove(position);
            notifyItemRemoved(position);
            viewModelC.delete(entry);

            Snackbar snackbar = Snackbar.make(
                    ((Activity) context).findViewById(android.R.id.content),
                    "ডাটা ডিলেট হয়েছে",
                    Snackbar.LENGTH_LONG
            );

            snackbar.setAction("আনডু", v -> {
                entries.add(position, entry);
                notifyItemInserted(position);
               // viewModelC.insert(entry);
                viewModelC.insertDEly(entry);
                //viewModelC.update(entry);
            });

            snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
            snackbar.show();
        }
    }*//*

    private void deleteWithUndo(CalendarEntryC entry) {
        int position = entries.indexOf(entry);
        if (position != -1) {
            entries.remove(position);
            notifyItemRemoved(position);

            if (deleteListener != null) {
                deleteListener.onItemDelete(entry); // Fragment থেকে delete করবে
            }

            Snackbar snackbar = Snackbar.make(
                    ((Activity) context).findViewById(android.R.id.content),
                    "ডাটা ডিলেট হয়েছে",
                    Snackbar.LENGTH_LONG
            );

            snackbar.setAction("আনডু", v -> {
                entries.add(position, entry);
                notifyItemInserted(position);
                if (deleteListener != null) {
                    deleteListener.onItemDelete(null); // Undo case, you may handle insertDEly here
                }
            });

            snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
            snackbar.show();
        }
    }


    private void showUpdateDialog(CalendarEntryC entry) {
        UpdateDialogC dialog = new UpdateDialogC(context, entry, updatedEntry -> {
            if (listener != null) {
                listener.onItemUpdate(updatedEntry);  // Safe callback
            }
        });
        dialog.show();
    }*/


   /* private void showUpdateDialog(CalendarEntryC entry) {
        UpdateDialogC updateDialog = new UpdateDialogC(context, entry, updatedEntry -> {
            viewModelC.update(updatedEntry);
            Toast.makeText(context, "ডাটা আপডেট করা হয়েছে", Toast.LENGTH_SHORT).show();
        });
        updateDialog.show();
    }*/


