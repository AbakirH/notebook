package com.example.notebook;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
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

    public String getStudents() {
        return getString(KEY_STUDENTS);
    }

    // String setStudents function to do
    public String getClassID() {
        return getString(KEY_STUDENTS);
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