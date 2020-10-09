package com.german.todoapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.german.todoapp.R;
import com.german.todoapp.database.LocalDatabase;
import com.german.todoapp.database.TasksDao;
import com.german.todoapp.models.Task;

public class AddTaskActivity extends BaseActivity implements View.OnClickListener {

    private EditText etTitle, etDescription, etAssignedTo;
    private Button btnSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        toolbarSetUp();
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etAssignedTo = findViewById(R.id.et_assigned_to);
        btnSaveTask = findViewById(R.id.btn_save_task);

        btnSaveTask.setOnClickListener(this);
    }

    private void toolbarSetUp() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Crear tarea");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (btnSaveTask == v) {
            getAndSaveTask();
        }
    }

    private void getAndSaveTask() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        String responsible = etAssignedTo.getText().toString();

        Task task = new Task(title, description, false, responsible);

        LocalDatabase database = LocalDatabase.getInstance(this);
        TasksDao dao = database.getTasksDao();
        dao.insert(task);

        clearInput();
        Toast.makeText(this, "Tarea creada!", Toast.LENGTH_SHORT).show();
    }

    private void clearInput() {
        etTitle.setText("");
        etDescription.setText("");
        etAssignedTo.setText("");
    }
}
