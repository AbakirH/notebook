package com.example.notebook;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Class")
public class Class extends ParseObject {
    public static final String KEY_CLASS_NAME = "className";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "teacherClass";
    public static final String KEY_STUDENTS = "students";
    public static final String KEY_CREATED_KEY = "createdAt";

    public String getClassName() {
        return getString(KEY_DESCRIPTION);
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

    public Date getCreatedKey() {
        return getDate(KEY_CREATED_KEY);
    }


}