package io.bitsound.android.sample;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


public class AppStorage {

    /* Internal Methods */
    private AppStorage(Context context) {

    }
    private static SharedPreferences local() {
        final Context context = App.getContext();
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    /* SharedPreference Method Mapping */
    public static boolean contains(String key) {
        return AppStorage.local().contains(key);
    }
    public static Map<String, ?> getAll() {
        return AppStorage.local().getAll();
    }
    public static boolean getBoolean(String key) {
        return AppStorage.getBoolean(key, false);
    }
    public static boolean getBoolean(String key, boolean fallback) {
        return AppStorage.local().getBoolean(key, fallback);
    }
    public static float getFloat(String key) {
        return AppStorage.getFloat(key, 0.0f);
    }
    public static float getFloat(String key, float fallback) {
        return AppStorage.local().getFloat(key, fallback);
    }
    public static int getInt(String key) {
        return AppStorage.getInt(key, 0);
    }
    public static int getInt(String key, int fallback) {
        return AppStorage.local().getInt(key, fallback);
    }
    public static long getLong(String key) {
        return AppStorage.getLong(key, 0L);
    }
    public static long getLong(String key, long fallback) {
        return AppStorage.local().getLong(key, fallback);
    }
    public static String getString(String key) {
        return AppStorage.getString(key, "");
    }
    public static String getString(String key, int fallback) {
        return AppStorage.getString(key, App.string(fallback));
    }
    public static String getString(String key, String fallback) {
        return AppStorage.local().getString(key, fallback);
    }

    /* SharedPreferences.Editor Method Mapping */
    public static void clear() {
        AppStorage.local().edit().clear().apply();
    }
    public static void put(String key, boolean value) {
        AppStorage.local().edit().putBoolean(key, value).apply();
    }
    public static void put(String key, float value) {
        AppStorage.local().edit().putFloat(key, value).apply();
    }
    public static void put(String key, int value) {
        AppStorage.local().edit().putInt(key, value).apply();
    }
    public static void put(String key, long value) {
        AppStorage.local().edit().putLong(key, value).apply();
    }
    public static void put(String key, String value) {
        AppStorage.local().edit().putString(key, value).apply();
    }
    public static void remove(String key) {
        AppStorage.local().edit().remove(key).apply();
    }
}
