package com.example.rezafd.smartvillagert.API;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends AppCompatActivity {
    APIRequest api;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public static final String SP_SmartVillage_APP = "spSmartVillageApp";

    public static final String SP_Username = "spUsername";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    public BaseActivity(Context context){
        sp =context.getSharedPreferences(SP_SmartVillage_APP,Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public BaseActivity() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);
        Retrofit base = new Retrofit.Builder()
//                .baseUrl("http://192.168.123.4/smartvillage/")
                .baseUrl("http://hmif.itenas.ac.id/smartvillage/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        api = base.create(APIRequest.class);
        sp = getSharedPreferences("Smartvillage", Context.MODE_PRIVATE);
    }
    public APIRequest getApi() {
        return api;
    }
    public SharedPreferences getSp() {
        return sp;
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

    public void makeErrorDialog(String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(msg);
        //alertDialogBuilder.setTitle(getString(R.string.title_error));
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void log(String log) {
        if (true) {
            Log.d("Login",log);
        }
    }
}
