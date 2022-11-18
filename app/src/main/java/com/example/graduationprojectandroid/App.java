package com.example.graduationprojectandroid;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class App extends Application {
    private static Resources resources;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
        context = getApplicationContext();
    }

    public static Resources getAppResources() {
        return resources;
    }
    public static Context getAppContext() {return context;}

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}