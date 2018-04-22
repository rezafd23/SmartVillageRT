package com.example.rezafd.smartvillagert.Common;

import android.location.Location;

import com.example.rezafd.smartvillagert.Model.User;
import com.example.rezafd.smartvillagert.Remote.FCMClient;
import com.example.rezafd.smartvillagert.Remote.IFCMService;
import com.example.rezafd.smartvillagert.Remote.IGoogleAPI;
import com.example.rezafd.smartvillagert.Remote.RetrofitClient;

public class Common {
    public static Location mLastLocation=null;
    public static  final String driver_tbl="Drivers";
    public static  final String user_driver_tbl="DriversInformation";
    public static  final String user_rider_tbl="RidersInformation";
    public static  final String pickup_request_tbl="PickupRequest";
    public static final String token_tbl="Tokens";

    public static User currentUser;

    public static double base_fare = 5000;
    public static double time_rate = 500;
    public static double distance_rate= 1000;

    public static double formulaPrice (double km, double min){
        return  (base_fare+(time_rate*min)+(distance_rate*km));
    }

    public static final String baseURL ="https://maps.googleapis.com";
    public static final String fcmURL ="https://fcm.googleapis.com/";

//    public static IGoogleAPI getGoogleAPI(){
//        return RetrofitClient.getClient(baseURL).create(IGoogleAPI.class);
//    }
    public static IFCMService getFCMService(){
        return FCMClient.getClient(fcmURL).create(IFCMService.class);
    }

}
