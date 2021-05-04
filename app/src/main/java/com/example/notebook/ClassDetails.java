package com.example.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ClassDetails extends AppCompatActivity {
    public static final String TAG = "createClass";
    private TextView className;
    private SwipeRefreshLayout swipeContainer;
    protected List<Student> allStudents;
    protected ClassDetailsAdapter adapter;
    private RecyclerView students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allStudents = new ArrayList<>();
        Intent myIntent = getIntent();
        setContentView(R.layout.class_details);
        className = findViewById(R.id.class_name);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        students = findViewById(R.id.students);


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "fetching data");
                queryStudents(Parcels.unwrap(myIntent.getParcelableExtra("students")));
                swipeContainer.setRefreshing(false);
            }
        });
        adapter = new ClassDetailsAdapter(this, allStudents);
        students.setAdapter(adapter);
        students.setLayoutManager(new LinearLayoutManager(this));
        queryStudents(Parcels.unwrap(myIntent.getParcelableExtra("students")));

        className.setText(myIntent.getStringExtra("className"));

        Log.i("Students", Parcels.unwrap(myIntent.getParcelableExtra("students")).toString());
//        etDescription = findViewById(R.id.description);
//        btnCreate = findViewById(R.id.btnCreate);

    }

    private void queryStudents(List<String> students) {
        for (String student: students) {
            Log.i("Students", student);
            ParseQuery<Student> query = ParseQuery.getQuery("Students");
            query.whereContains(Student.KEY_OBJECT_ID, student);
            Log.i(TAG, ParseUser.getCurrentUser().getObjectId());
            query.setLimit(20);
            query.findInBackground(new FindCallback<Student>() {
                @Override
                public void done(List<Student> students, ParseException e) {
                    if (e != null) {
                        Log.e(TAG, "Issue with getting posts", e);
                        return;
                    }
                    for (Student createdStudent : students) {
                        Log.i(TAG, "Class Name: " + createdStudent.getName());
//                    Toast.makeText(getContext(), "Class Name: " + createdClass.getClassName()
//                            + "Class Description: " + createdClass.getDescription(), Toast.LENGTH_LONG).show();
                    }
                    allStudents.addAll(students);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }


}
