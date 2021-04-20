package com.example.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private ImageView ivInstagram;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ParseUser.getCurrentUser() != null) {
            if(ParseUser.getCurrentUser().get("Role").equals("Teacher")){
                Log.i(TAG, "Role is " + ParseUser.getCurrentUser().get("Role"));
                goToActivity(com.example.notebook.MainActivity.class);
            }else {
                goToActivity(com.example.notebook.StudentActivity.class);
            }
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(v -> {
            setContentView(R.layout.activity_login);
            Log.i(TAG, "onClick login button");
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            loginUser(username, password);
        });
//
        btnSignUp.setOnClickListener(v -> {
            Log.i(TAG, "onClick signup button");
            goToActivity(com.example.notebook.SignupActivity.class);
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, (user, e) -> {
            if(e != null) {
                Log.e(TAG, "Problem with login", e);
                Toast.makeText(com.example.notebook.LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                goToActivity(com.example.notebook.LoginActivity.class);
            }else {
                if (ParseUser.getCurrentUser().get("Role").equals("Teacher")) {
                    Log.i(TAG, "Role is " + ParseUser.getCurrentUser().get("Role"));
                    goToActivity(com.example.notebook.MainActivity.class);
                } else {
                    goToActivity(com.example.notebook.StudentActivity.class);
                }
            }
            //ParseUser.getCurrentUser().pinInBackground();
        });

    }
    private void goToActivity(java.lang.Class activity) {
        Intent i = new Intent(this, activity);
        startActivity(i);
        finish();

    }
}