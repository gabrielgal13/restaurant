package com.lucas.restaurante.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucas.restaurante.dao.Category;
import com.lucas.restaurante.dao.Food;


import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public interface StateElementsManager {

    static void saveState(Context ct, Object object, String location) {
        SharedPreferences sharedPreferences = ct.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(location, json);
        editor.apply();
    }

    static Object loadState(Context ct, String location) {
        Object catList = new Object();
        SharedPreferences sharedPreferences = ct.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(location, null);
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        catList = gson.fromJson(json, type);
        return catList;
    }

    static Object loadState(Context ct, String location, Boolean food) {
        Object catList = new Object();
        SharedPreferences sharedPreferences = ct.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(location, null);
        Type type = new TypeToken<ArrayList<Food>>() {}.getType();
        catList = gson.fromJson(json, type);
        return catList;
    }



}
