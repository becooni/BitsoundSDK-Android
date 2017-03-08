package io.bitsound.android.sample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import timber.log.Timber;


public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context = null;
    public static Context getContext() {
        return App.context;
    }

    public static String string(int resid) {
        return App.context.getString(resid);
    }

    public static int color(int resid) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return App.context.getResources().getColor(resid);
        } else {
            return App.context.getColor(resid);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /* Application Context */
        App.context = this;

        /* Timber Initialization | TODO : Apply BuildConfig DEBUG Flag on release */
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
    }
}
