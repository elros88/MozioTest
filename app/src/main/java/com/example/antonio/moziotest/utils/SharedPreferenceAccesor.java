package com.example.antonio.moziotest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by antonio on 7/27/17.
 */

public class SharedPreferenceAccesor {

    private final SharedPreferences.Editor editor;
    private final SharedPreferences preferences;

    public SharedPreferenceAccesor(Context context)
    {
        preferences = context.getSharedPreferences("apiInfo", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public String saveObject (Object object, String tag)
    {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(tag, json);
        editor.apply();
        return json;
    }

    public String getPreference (String tag)
    {
        return preferences.getString(tag, "");
    }

    public SharedPreferences getPreferences()
    {
        return preferences;
    }
}
