package com.example.rezafd.smartvillagert.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {
//    private static final String base_url = "http://192.168.123.4/smartvillage/";
    private static final String base_url = "http://hmif.itenas.ac.id/smartvillage/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
