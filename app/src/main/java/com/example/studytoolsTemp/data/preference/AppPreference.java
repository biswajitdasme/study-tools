package com.example.studytoolsTemp.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studytoolsTemp.models.User;

import static com.example.studytoolsTemp.data.preference.PreferenceKey.APP_PREFERENCE;
import static com.example.studytoolsTemp.data.preference.PreferenceKey.EMAIL;
import static com.example.studytoolsTemp.data.preference.PreferenceKey.ID;
import static com.example.studytoolsTemp.data.preference.PreferenceKey.MOBILE;
import static com.example.studytoolsTemp.data.preference.PreferenceKey.NAME;
import static com.example.studytoolsTemp.data.preference.PreferenceKey.PASSWORD;
import static com.example.studytoolsTemp.data.preference.PreferenceKey.USERNAME;

public class AppPreference {

    public static void setData(Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();

        prefEditor.putString(ID, user.getId());
        prefEditor.putString(NAME, user.getName());
        prefEditor.putString(EMAIL, user.getEmail());
        prefEditor.putString(MOBILE, user.getMobile());
        prefEditor.putString(USERNAME, user.getUsername());
        prefEditor.putString(PASSWORD, user.getPassword());

        prefEditor.apply();
    }

    public static User getData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);

        String id = sharedPreferences.getString(ID, "");
        String name = sharedPreferences.getString(NAME, "");
        String email = sharedPreferences.getString(EMAIL, "");
        String mobile = sharedPreferences.getString(MOBILE, "");
        String username = sharedPreferences.getString(USERNAME, "");
        String password = sharedPreferences.getString(PASSWORD, "");

        User user = new User(id, name, email, mobile, username, password);

        return user;
    }

    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);

        return sharedPreferences.getString(ID, "");
    }

    public static void clearData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        sharedPrefEditor.clear();

        sharedPrefEditor.apply();
    }
}
