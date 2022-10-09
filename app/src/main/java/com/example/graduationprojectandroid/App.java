package com.example.graduationprojectandroid;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class App extends Application {
    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
    }

    public static Resources getAppResources() {
        return resources;
    }
}