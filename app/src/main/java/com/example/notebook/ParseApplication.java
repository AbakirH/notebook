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
                .applicationId("QlaH8NKkwp0l56xHMg33RytAeM4LMdSC3m2nAzDe")
                .clientKey("6Mgq1Y6hoEPs1aqujn4qqJJMVTcNs2reMuZF707H")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
