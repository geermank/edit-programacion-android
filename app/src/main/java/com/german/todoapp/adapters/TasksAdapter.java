package com.german.todoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.german.todoapp.R;
import com.german.todoapp.models.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private List<Task> tasks;

    public TasksAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView assignedTo;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_task_title);
            assignedTo = itemView.findViewById(R.id.tv_assigned_to);
        }

        public void bind(Task task) {
            title.setText(task.getTitle());
            assignedTo.setText(task.getAssignedTo());
        }
    }
}
