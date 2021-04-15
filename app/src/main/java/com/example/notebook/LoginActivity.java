package com.example.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notebook.R;
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
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(v -> {
            Log.i(TAG, "onClick login button");
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            loginUser(username, password);
        });
//
        btnSignUp.setOnClickListener(v -> {
            Log.i(TAG, "onClick signup button");
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            signupUser(username, password);
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, (user, e) -> {
            if(e != null) {
                Log.e(TAG, "Problem with login", e);
                Toast.makeText(com.example.notebook.LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                return;
            }
            goMainActivity();
            //ParseUser.getCurrentUser().pinInBackground();
        });

    }

    private void signupUser(String username, String password) {
        Log.i(TAG, "Attempting to signup user " + username);
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(e -> {
            if (e != null) {
                Log.e(TAG, "Issue with signup", e);
                Toast.makeText(com.example.notebook.LoginActivity.this, "Issue with signup!", Toast.LENGTH_SHORT).show();
                return;
            }
            goMainActivity();
            //ParseUser.getCurrentUser().pinInBackground();
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, com.example.notebook.MainActivity.class);
        startActivity(i);
        finish();
    }
}