package com.example.notebook;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nLmMYQRcXnpMLKkDQFl7C0PMv9Y8pUzmWAAe665D")
                .clientKey("phse6YoGHaJcI8SplhSm6wB1fZwmQN0b7GicNnNk")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
