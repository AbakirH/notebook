package com.example.notebook.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.notebook.Class;
import com.example.notebook.ClassDetails;
import com.example.notebook.ClassesAdapter;
import com.example.notebook.R;
import com.example.notebook.Student;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class GradesFragment extends Fragment {

    public static final String TAG = "GradesFragment";
    private  Spinner spinner2;
    private Spinner spinner;
    protected List<String> allClasses = new ArrayList<>();
    protected List<String> allStudentsUser = new ArrayList<>();;
    protected List<String> allStudents = new ArrayList<>();;
    private String studentClass;
    private String student;
    private EditText gradeText;
    private Button inputGrades;
    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = (Spinner) view.findViewById(R.id.studentClass);
        allClasses.add("Pick a Class");
        spinner2 = (Spinner) view.findViewById(R.id.student);
        allStudents.add("Pick a Student");
        gradeText = view.findViewById(R.id.gradeEdit);
        inputGrades = view.findViewById(R.id.inputGrades);

        queryClasses();
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.spinner_grade_item, allClasses);
        dataAdapter.setDropDownViewResource(R.layout.spinner_grade_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentClass = (String) parent.getItemAtPosition(position);
                queryStudents(studentClass);

                ArrayAdapter<String> dataAdapter2 =
                        new ArrayAdapter<String>(getContext(), R.layout.spinner_grade_item, allStudents);
                dataAdapter2.setDropDownViewResource(R.layout.spinner_grade_item);
                spinner2.setAdapter(dataAdapter2);

                Log.i(TAG, "Selected : " + studentClass);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                student = (String) parent.getItemAtPosition(position);
                Log.i(TAG, "Selected : " + student);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        inputGrades.setOnClickListener(v -> {
            String grade = gradeText.getText().toString();
            updateGrade(grade);
        });
    }

    protected void queryClasses() {
        // Specify which class to query

        ParseQuery<Class> query = ParseQuery.getQuery("Class");
        query.whereContains(Class.KEY_USER, ParseUser.getCurrentUser().getObjectId());
        Log.i(TAG, ParseUser.getCurrentUser().getObjectId());
        query.setLimit(20);
        query.findInBackground(new FindCallback<Class>() {
            @Override
            public void done(List<Class> Classes, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Class createdClass: Classes) {
                    Log.i(TAG, "Class Name: " + createdClass.getClassName() + "Class Description: " + createdClass.getDescription());
//                    Toast.makeText(getContext(), "Class Name: " + createdClass.getClassName()
//                            + "Class Description: " + createdClass.getDescription(), Toast.LENGTH_LONG).show();
                    allClasses.add(createdClass.getClassName());
                }
            }
        });
    }

    private void queryStudents(String studentClass) {
        Log.i(TAG, "Selected : "+ studentClass);
        ParseQuery<Class> query = ParseQuery.getQuery("Class");
        query.whereContains(Class.KEY_CLASS_NAME, studentClass);
        Log.i(TAG, ParseUser.getCurrentUser().getObjectId());
        query.setLimit(1);
        query.findInBackground(new FindCallback<Class>() {
            @Override
            public void done(List<Class> Classes, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Class createdClass: Classes) {
                    allStudentsUser = createdClass.getStudents();
                    Log.i(TAG, "Students: " + allStudentsUser.toString());
                    for (String student: allStudentsUser) {
                        Log.i("Students", student);
                        ParseQuery<Student> query2 = ParseQuery.getQuery("Students");
                        query2.whereContains(Student.KEY_OBJECT_ID, student);
                        Log.i(TAG, ParseUser.getCurrentUser().getObjectId());
                        query2.setLimit(20);
                        query2.findInBackground(new FindCallback<Student>() {
                            @Override
                            public void done(List<Student> students, ParseException e) {
                                if (e != null) {
                                    Log.e(TAG, "Issue with getting posts", e);
                                    return;
                                }
                                for (Student createdStudent : students) {
                                    Log.i(TAG, "Student Name: " + createdStudent.getName());
                                    allStudents.add(createdStudent.getName());
                                }

                            }
                        });
                    }
                }
            }
        });


    }
    private void updateGrade(String grade) {
        ParseQuery<Student> query = ParseQuery.getQuery("Students");
        Log.i(TAG, student);
        query.whereEqualTo(Student.KEY_Student_NAME, student);
        query.getFirstInBackground(new GetCallback<Student>() {
            @Override
            public void done(Student student, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting the Student to change his grade", e);
                    return;
                }else {
                    student.setGrade(grade);
                    student.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e != null){
                                Log.e(TAG, "Error while saving", e);
                                Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                            }
                            Log.i(TAG, "Student Grade Changed successful!");
                        }
                    });
                }
            }
        });
    }
    // Clean all elements of the recycler
}