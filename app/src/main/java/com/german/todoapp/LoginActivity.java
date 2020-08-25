package com.german.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Estas variables heredan de View
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);

        btnLogin.setOnClickListener(this);

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Olvide mi contraseña", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            startLoginFlow();
        }
    }

    private void startLoginFlow() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (notValidInput(email, password)) {
            Toast.makeText(this, "Las credenciales no son válidas!", Toast.LENGTH_SHORT).show();
            return;
        }
        startMainActivity(email, password);
    }

    private boolean notValidInput(String email, String password) {
        return email.isEmpty() || password.isEmpty() || !email.contains("@") || password.length() < Constants.MIN_PASSWORD_LENGTH;
    }

    private void startMainActivity(String email, String password) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(Constants.USER_EMAIL, email);
        mainIntent.putExtra(Constants.USER_PASSWORD, password);
        startActivity(mainIntent);
        finish();
    }
}
