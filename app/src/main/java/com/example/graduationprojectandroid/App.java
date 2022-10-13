package com.example.graduationprojectandroid;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}