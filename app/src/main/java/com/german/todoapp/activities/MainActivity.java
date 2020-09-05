package com.german.todoapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.german.todoapp.AddTaskActivity;
import com.german.todoapp.R;
import com.german.todoapp.adapters.OnItemClickListener;
import com.german.todoapp.adapters.TasksAdapter;
import com.german.todoapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements OnItemClickListener {

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
        toolbarSetUp();
        tasksListSetUp();
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
            Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Object value) {
        Task task = (Task) value;
        Toast.makeText(this, task.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private void tasksListSetUp() {
        RecyclerView rvTasks = findViewById(R.id.rv_tasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        List<Task> tasks = createTasks();
        TasksAdapter adapter = new TasksAdapter(tasks);
        adapter.setOnItemClickListener(this);

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
