package com.selfapps.dok.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.selfapps.dok.App;
import com.selfapps.dok.model.entity.DataType;

import static com.selfapps.dok.utils.Constants.*;

public class PreferencesUtil {
    private static SharedPreferences preferences;

    public static SharedPreferences getInstance(Context context){
        if(preferences == null){
            preferences = context.getSharedPreferences( PREF_INIT_KEY, Context.MODE_PRIVATE);
        }
        return preferences;
    }


    public static String getData(DataType type){
         return getString(type.toString(),"");
    }

    public static void saveData(DataType type, String content){
        setString(type.toString(),content);
    }

    public static String getString(String key,@Nullable String defValue){
        getInstance(App.getContext());
        return preferences.getString(key,defValue);
    }

    public static int getInt(String key,@Nullable int defValue){
        getInstance(App.getContext());
        return preferences.getInt(key,defValue);
    }

    public static long getLong(String key,@Nullable long defValue){
        getInstance(App.getContext());
        return preferences.getLong(key,defValue);
    }

    public static int getBoolean(String key,@Nullable int defValue){
        getInstance(App.getContext());
        return preferences.getInt(key,defValue);
    }


    public static void setString(@NonNull String key, String value){
        getInstance(App.getContext());
        preferences.edit().putString(key, value).apply();
    }

    public static void setInt(@NonNull String key, int value){
        getInstance(App.getContext());
        preferences.edit().putInt(key, value).apply();
    }

    public static void setLong(@NonNull String key, long value){
        getInstance(App.getContext());
        preferences.edit().putLong(key, value).apply();
    }

    public static void setBoolean(@NonNull String key, boolean value){
        getInstance(App.getContext());
        preferences.edit().putBoolean(key, value).apply();
    }
}
