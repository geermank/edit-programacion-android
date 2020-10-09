package com.german.todoapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.german.todoapp.Constants;
import com.german.todoapp.R;
import com.german.todoapp.adapters.OnItemClickListener;
import com.german.todoapp.adapters.TasksAdapter;
import com.german.todoapp.database.LocalDatabase;
import com.german.todoapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements OnItemClickListener {

    private List<Task> tasks;
    private TasksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intent intent = getIntent();
        String email = intent.getStringExtra(Constants.USER_EMAIL);
        String password = intent.getStringExtra(Constants.USER_PASSWORD);
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        TextView message = findViewById(R.id.tv_hello_world);
        String helloMessage = getString(R.string.welcome_message);
        message.setText(helloMessage);
        message.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));*/
        tasks = new ArrayList<>();
        toolbarSetUp();
        tasksListSetUp();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateTasks();
        adapter.notifyDataSetChanged();
    }

    private void toolbarSetUp() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Listado de tareas");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_task) {
            startActivity(new Intent(this, AddTaskActivity.class));
        } else if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE);
        if (preferences.contains(Constants.USER_EMAIL) && preferences.contains(Constants.USER_PASSWORD)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onItemClick(Object value) {
        Task task = (Task) value;
        Toast.makeText(this, task.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private void tasksListSetUp() {
        RecyclerView rvTasks = findViewById(R.id.rv_tasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        updateTasks();
        adapter = new TasksAdapter(tasks);
        adapter.setOnItemClickListener(this);

        rvTasks.setAdapter(adapter);
    }

    private void updateTasks() {
        List<Task> tasksFromDatabase = LocalDatabase.getInstance(this).getTasksDao().getAll();
        tasks.clear();
        tasks.addAll(tasksFromDatabase);
    }
}
