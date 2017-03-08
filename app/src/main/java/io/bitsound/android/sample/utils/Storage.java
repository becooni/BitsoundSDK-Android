package io.bitsound.android.sample.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import io.bitsound.android.sample.App;


public class Storage {

    /* Internal Methods */
    private Storage(Context context) {

    }
    private static SharedPreferences local() {
        final Context context = App.getContext();
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    /* SharedPreference Method Mapping */
    public static boolean contains(String key) {
        return Storage.local().contains(key);
    }
    public static Map<String, ?> getAll() {
        return Storage.local().getAll();
    }
    public static boolean getBoolean(String key) {
        return Storage.getBoolean(key, false);
    }
    public static boolean getBoolean(String key, boolean fallback) {
        return Storage.local().getBoolean(key, fallback);
    }
    public static float getFloat(String key) {
        return Storage.getFloat(key, 0.0f);
    }
    public static float getFloat(String key, float fallback) {
        return Storage.local().getFloat(key, fallback);
    }
    public static int getInt(String key) {
        return Storage.getInt(key, 0);
    }
    public static int getInt(String key, int fallback) {
        return Storage.local().getInt(key, fallback);
    }
    public static long getLong(String key) {
        return Storage.getLong(key, 0L);
    }
    public static long getLong(String key, long fallback) {
        return Storage.local().getLong(key, fallback);
    }
    public static String getString(String key) {
        return Storage.getString(key, "");
    }
    public static String getString(String key, int fallback) {
        return Storage.getString(key, App.string(fallback));
    }
    public static String getString(String key, String fallback) {
        return Storage.local().getString(key, fallback);
    }

    /* SharedPreferences.Editor Method Mapping */
    public static void clear() {
        Storage.local().edit().clear().apply();
    }
    public static void put(String key, boolean value) {
        Storage.local().edit().putBoolean(key, value).apply();
    }
    public static void put(String key, float value) {
        Storage.local().edit().putFloat(key, value).apply();
    }
    public static void put(String key, int value) {
        Storage.local().edit().putInt(key, value).apply();
    }
    public static void put(String key, long value) {
        Storage.local().edit().putLong(key, value).apply();
    }
    public static void put(String key, String value) {
        Storage.local().edit().putString(key, value).apply();
    }
    public static void remove(String key) {
        Storage.local().edit().remove(key).apply();
    }
}
