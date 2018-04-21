package com.example.rezafd.smartvillagert.API;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingSession {
    public static final String SP_SmartVillage_APP = "spSmartVillageApp";

    public static final String SP_Username = "spUsername";
//    public static final String SP_EMAIL = "spEmail";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    public SettingSession(Context context){
        sp =context.getSharedPreferences(SP_SmartVillage_APP,Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }
    public SettingSession() {
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public static String getSP_Username() {
        return SP_Username;
    }

    public void setPreference(Context context, String key, String value){
        SharedPreferences.Editor editor = context.getSharedPreferences("Smartvillage",Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
    }

    public String getPreference(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences("Smartvillage",Context.MODE_PRIVATE);
        String position = preferences.getString(key,"");
        return position;
    }

}
