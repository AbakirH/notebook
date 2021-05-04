package com.example.notebook;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Students")
public class Student extends ParseObject {
    public static final String KEY_Student_NAME = "name";
    public static final String KEY_GRADE = "grade";
    public static final String KEY_OBJECT_ID = "userID";
    private String name;
    private String objectId;
    private String grade;
    public Student(){}

    public Student(String name, String objectId){
        this.name = name;
        this.objectId = objectId;
        grade = "-";
    }

    public String getName() {
        return getString(KEY_Student_NAME);
    }

    public void setName(String name) {
        put(KEY_Student_NAME, name);
    }
    public String getGrade() {
        return getString(KEY_GRADE);
    }

    public void setGrade(String grade) {
        put(KEY_GRADE, grade);
    }

    public void setObjectID(String objectID) {
        put(KEY_OBJECT_ID, objectID);
    }
    public String getKeyObjectId() {
        return getString(KEY_OBJECT_ID);
    }
}
