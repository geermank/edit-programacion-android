package com.german.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String email = intent.getStringExtra(Constants.USER_EMAIL);
        String password = intent.getStringExtra(Constants.USER_PASSWORD);
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
    }
}
