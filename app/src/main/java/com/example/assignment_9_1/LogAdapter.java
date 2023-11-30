package com.example.assignment_9_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_9_1.databinding.ListItemEntryBinding;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private List<Log> logs;

    public LogAdapter(List<Log> logs) {
        this.logs = logs;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemEntryBinding binding = ListItemEntryBinding.inflate(layoutInflater, parent, false);
        return new LogViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        Log log = logs.get(position);
        holder.bind(log);
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {

        private final ListItemEntryBinding binding;

        public LogViewHolder(ListItemEntryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Log log) {
            binding.textViewTime.setText(String.valueOf(log.getDateTime().getTime()));
            binding.textViewSleep.setText(log.getSleepHrs());
            binding.textViewExercise.setText(log.getExerciseMins());
            binding.textViewWeight.setText(log.getWeight());

            // Handle delete button click
            binding.buttonDelete.setOnClickListener(view -> {
                // call the listener to delete the item
                mListener.deleteLog(log);
            });
        }
    }

    static LogAdapterListener mListener;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mListener = (LogAdapterListener) recyclerView.getContext();
    }

    public interface LogAdapterListener {
        void deleteLog(Log log);
    }
}

