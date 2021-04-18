package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private ImageView ivInstagram;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "signup page switched ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Spinner spinner = (Spinner) findViewById(R.id.role);
        List<String> roles = new ArrayList<String>();
        roles.add("Teacher");
        roles.add("Student");

        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(v -> {
            Log.i(TAG, "Switched to login Screen");
            goToActivity(com.example.notebook.LoginActivity.class);
        });
//
        btnSignUp.setOnClickListener(v -> {
            Log.i(TAG, "onClick signup button");
            setContentView(R.layout.activity_signup);
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            signupUser(username, password);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = (String) parent.getItemAtPosition(position);
                Log.i(TAG, "Selected : " + role);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void signupUser(String username, String password) {
        Log.i(TAG, "Attempting to signup user " + username);
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.put("Role", role);
        // Invoke signUpInBackground
        user.signUpInBackground(e -> {
            if (e != null) {
                Log.e(TAG, "Issue with signup", e);
                Toast.makeText(com.example.notebook.SignupActivity.this, "Issue with signup!", Toast.LENGTH_SHORT).show();
                goToActivity(com.example.notebook.SignupActivity.class);
            }else {
                if (role.equals("Teacher")) {
                    Log.i(TAG, "Role is " + ParseUser.getCurrentUser().get("Role"));
                    goToActivity(com.example.notebook.MainActivity.class);
                } else {
                    goToActivity(com.example.notebook.StudentActivity.class);
                }
            }
            //ParseUser.getCurrentUser().pinInBackground();
        });
    }
    private void goToActivity(Class activity) {
        Intent i = new Intent(this, activity);
        startActivity(i);
        finish();

    }
}
