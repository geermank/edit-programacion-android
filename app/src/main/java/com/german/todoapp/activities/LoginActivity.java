package com.german.todoapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.german.todoapp.Constants;
import com.german.todoapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Estas variables heredan de View
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword;
    private CheckBox cbRememberCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        cbRememberCredentials = findViewById(R.id.cb_remember_credentials);

        btnLogin.setOnClickListener(this);

        // clases anonimas
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Olvide mi contraseña", Toast.LENGTH_SHORT).show();
            }
        });

        // aca deberiamos validar si el usuario tiene mail y password almacenados
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString(Constants.USER_EMAIL, null);
        String password = sharedPreferences.getString(Constants.USER_PASSWORD, null);
        if (email != null && password != null) {
            startMainActivity(email, password);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            saveUserCredentials(email, password);
            startMainActivity(email, password);
        }
    }

    private void saveUserCredentials(String email, String password) {
        if (!cbRememberCredentials.isChecked()) {
            return;
        }
        /*SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.USER_EMAIL, email);
        editor.putString(Constants.USER_PASSWORD, password);
        editor.apply();*/
        getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE).edit()
                .putString(Constants.USER_EMAIL, email)
                .putString(Constants.USER_PASSWORD, password)
                .apply();
    }

    private void startMainActivity(String email, String password) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(Constants.USER_EMAIL, email);
        mainIntent.putExtra(Constants.USER_PASSWORD, password);
        startActivity(mainIntent);
        finish();
    }
}
