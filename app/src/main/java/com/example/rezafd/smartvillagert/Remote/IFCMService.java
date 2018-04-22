package com.example.rezafd.smartvillagert.Remote;

import com.example.rezafd.smartvillagert.Model.FCMResponse;
import com.example.rezafd.smartvillagert.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAHlCD2Vk:APA91bFaGVPz1BomDmVknQG9A_a_fb23c6jCvV_uqz1qaYt-1axCuvg08VDgwH5nEg7fmOruFTFTTE-EGxWKJ2cGQKd9HP_PQJ8U-Kuw5I9kp79PDZGnQIGxDw7CJKHUqvwKmCHGw6K5"
    })
    @POST("fcm/send")
    Call<FCMResponse> sendMessage(@Body Sender body);
}
