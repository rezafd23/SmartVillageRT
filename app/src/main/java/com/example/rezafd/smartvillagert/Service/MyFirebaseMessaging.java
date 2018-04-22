package com.example.rezafd.smartvillagert.Service;

import android.content.Intent;

import com.example.rezafd.smartvillagert.API.SettingSession;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService{
    private String Username,Alamat;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//
//        SettingSession session = new SettingSession();
//        Username=session.getPreference(getBaseContext(),"Username");
//        Alamat=session.getPreference(getBaseContext(),"Alamat");
//
//        Intent intent = new Intent(getBaseContext(), CustomerCall.class);
//        intent.putExtra("Username",Username);
//        intent.putExtra("Alamat",Alamat);
//        startActivity(intent);


    }
}
