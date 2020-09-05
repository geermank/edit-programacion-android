package com.german.todoapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.german.todoapp.R;
import com.german.todoapp.adapters.TasksAdapter;
import com.german.todoapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intent intent = getIntent();
        String email = intent.getStringExtra(Constants.USER_EMAIL);
        String password = intent.getStringExtra(Constants.USER_PASSWORD);
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();*/
        TextView message = findViewById(R.id.tv_hello_world);
        String helloMessage = getString(R.string.welcome_message);
        message.setText(helloMessage);
        message.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

        RecyclerView rvTasks = findViewById(R.id.rv_tasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        List<Task> tasks = createTasks();
        TasksAdapter adapter = new TasksAdapter(tasks);
        rvTasks.setAdapter(adapter);
    }

    private List<Task> createTasks() {
        Task t1 = new Task();
        t1.setTitle("Pasear al perro");
        t1.setAssignedTo("German");

        Task t2 = new Task();
        t2.setTitle("Lavar la rop");
        t2.setAssignedTo("German");

        Task t3 = new Task();
        t3.setTitle("Ir al supermercado");
        t3.setAssignedTo("Mi abuela");

        List<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        return tasks;
    }

}
