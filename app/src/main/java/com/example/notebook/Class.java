package com.example.notebook;

import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@ParseClassName("Class")
public class Class extends ParseObject {
    public static final String TAG = "Class";

    public static final String KEY_CLASS_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "teacherClass";
    public static final String KEY_STUDENTS = "students";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_CLASS_ID = "classID";
    private int rand;
    private boolean isUniqueKey;

    public String getClassName() {
        return getString(KEY_CLASS_NAME);
    }

    public void setClassName(String name) {
        put(KEY_CLASS_NAME, name);
    }
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public List<String> getStudents() {
        return getList(KEY_STUDENTS);
    }

    public void setStudents(List<String> students) {
        put(KEY_STUDENTS, students);
    }

    public void addStudent(String name, String user) {
        Student student = new Student(name, user);
        student.setName(name);
        student.setGrade("-");
        student.setObjectID(user);
        Log.i(TAG, student.getClassName());

        ParseQuery<Student> query = ParseQuery.getQuery("Students");
        Log.i(TAG, user);
        query.whereContains(Student.KEY_OBJECT_ID, user);
        query.setLimit(2);
        query.findInBackground(new FindCallback<Student>() {
            @Override
            public void done(List<Student> students, ParseException e) {
                if (students.isEmpty()) {
                    Log.e(TAG, students.toString() + "user already exists " + user, e);
                    student.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Log.e(TAG, "Error while saving", e);
                            }
                            Log.i(TAG, "Student saved successful!");
                        }
                    });
                    return;
                } else {
                    Log.e(TAG, students.toString() + "user already exists " + student.getName(), e);
                }
            }

        });
        List<String> students = getStudents();
        students.add(user);
        put(KEY_STUDENTS, students);

    }


    public Number getClassID() {
        return getNumber(KEY_CLASS_ID);
    }

    public void setClassID() {
        Random r = new Random();
        rand =  r.nextInt(10000);
//        isUniqueKey = false;
//        while (!isUniqueKey){
//            ParseQuery<ParseObject> query = ParseQuery.getQuery("Class");
//            query.whereEqualTo(KEY_CLASS_ID, rand);
//            Log.e(TAG, "Start");
//            query.getFirstInBackground(new GetCallback<ParseObject>() {
//                public void done(ParseObject player, ParseException e) {
//                    if (e == null) {
//                        rand = r.nextInt(10000);
//                        Log.e(TAG, "Found rand number", e);
//                    } else {
//                        isUniqueKey = true;
//                        Log.i(TAG, "Did not find random number ");
//                    }
//                }
//            });
//        }
        put(KEY_CLASS_ID, rand);
    }

    public Date getCreatedKey() {
        return getDate(KEY_CREATED_KEY);
    }


}