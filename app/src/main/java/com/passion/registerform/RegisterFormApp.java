package com.passion.registerform;

import android.app.Application;
import android.content.Context;

public class RegisterFormApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        context = null;
    }

    public static Context getContext() {
        return context;
    }
}
