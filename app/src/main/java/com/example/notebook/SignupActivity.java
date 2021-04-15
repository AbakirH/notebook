package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseUser;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private ImageView ivInstagram;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "signup page switched ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(v -> {
            Log.i(TAG, "Switched to login Screen");
            goLoginActivity();
        });
//
        btnSignUp.setOnClickListener(v -> {
            Log.i(TAG, "onClick signup button");
            setContentView(R.layout.activity_signup);
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            signupUser(username, password);
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
                Toast.makeText(com.example.notebook.SignupActivity.this, "Issue with signup!", Toast.LENGTH_SHORT).show();
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
    private void goLoginActivity() {
        Intent i = new Intent(this, com.example.notebook.LoginActivity.class);
        startActivity(i);
        finish();
    }
}
