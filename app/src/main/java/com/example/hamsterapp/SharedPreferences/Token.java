package com.example.hamsterapp.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Token {
        private static final String KEY_TOKEN = "token";
        private static final Boolean KEY_IS_LOGGED_IN = false;

        public static void saveToken(Context context, String token) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_TOKEN, token);
            editor.apply();
        }

        public static void saveIsLoggedIn(Context context, Boolean isLoggedIn) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(String.valueOf(KEY_IS_LOGGED_IN), isLoggedIn);
            editor.apply();
        }

        public static String getToken(Context context) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            return preferences.getString(KEY_TOKEN, null);
        }

        public static void clearToken(Context context) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(KEY_TOKEN);
            editor.apply();
        }

        public static Boolean getIsLoggedIn(Context context) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            return preferences.getBoolean(String.valueOf(KEY_IS_LOGGED_IN), false);
        }
    }

