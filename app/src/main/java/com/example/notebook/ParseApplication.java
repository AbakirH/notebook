package com.example.notebook;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Class.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("XiIEevBKglHeypom5ZG176c3aROCJcJwd9lFbevo")
                .clientKey("SrWtdd3eL5VaQClX0IfHvB5OnZ6HG7Mjfsn0iEsS")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
