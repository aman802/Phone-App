package com.aman802.phoneapp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SharedPreference {

    public static int getThemePreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(context.getString(R.string.theme), 0);
    }

    public static void setThemePreference(Context context, int themeType) {
        SharedPreferences sharedPreference = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(context.getString(R.string.theme), themeType);
        editor.apply();
    }

    public static int getSelectedBottomTab(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(context.getString(R.string.selected_bottom_tab), 1);
    }

    public static void setSelectedBottomTab(Context context, int tabPosition) {
        SharedPreferences sharedPreference = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(context.getString(R.string.selected_bottom_tab), tabPosition);
        editor.apply();
    }

    public static boolean isSearchActive(Context context) {
        SharedPreferences sharedPreference = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        return sharedPreference.getBoolean(context.getString(R.string.search_shared_preference), false);
    }

    public static void setSearchActive(Context context, boolean value) {
        SharedPreferences sharedPreference = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(context.getString(R.string.search_shared_preference), value);
        editor.apply();
    }

    public static long getLastDefaultAppCheck(Context context) {
        SharedPreferences sharedPreference = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        return sharedPreference.getLong(context.getString(R.string.default_app), -1);
    }

    public static void setLastDefaultAppCheck(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        long millis = System.currentTimeMillis();
        editor.putLong(context.getString(R.string.default_app), millis);
        editor.apply();
    }

    public static String getCurrentCallerName(Context context) {
        SharedPreferences sharedPreference = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        return sharedPreference.getString(context.getString(R.string.current_called_name), null);
    }

    public static void setCurrentCallerName(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.current_called_name), name);
        editor.apply();
    }
}
