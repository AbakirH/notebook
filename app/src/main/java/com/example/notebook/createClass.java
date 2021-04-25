package com.example.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Random;

public class createClass extends AppCompatActivity {
    public static final String TAG = "createClass";
    private EditText classname;
    private EditText etDescription;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_create);

        classname = findViewById(R.id.className);
        etDescription = findViewById(R.id.description);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                String className = classname.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(createClass.this,"Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }else if(className.isEmpty()){
                    Toast.makeText(createClass.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser teacherUser = ParseUser.getCurrentUser();
                saveClass(teacherUser, className, description);
            }
        });
    }

    private void saveClass(ParseUser teacherUser, String className, String description) {
        Class createdClass = new Class();
        createdClass.setUser(teacherUser);
        createdClass.setClassName(className);
        createdClass.setDescription(description);
        createdClass.setClassID();

        Log.i(TAG, createdClass.getClassName());
        createdClass.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(createClass.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Class saved successful!");
                classname.setText("");
                etDescription.setText("");
                goToActivity(com.example.notebook.MainActivity.class);
            }
        });
    }
    private void goToActivity(java.lang.Class activity) {
        Intent i = new Intent(this, activity);
        startActivity(i);
        finish();
    }

}
