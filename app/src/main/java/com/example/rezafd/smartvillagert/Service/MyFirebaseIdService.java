package com.example.rezafd.smartvillagert.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class MyFirebaseIdService  {
//
//    @Override
//    public void onTokenRefresh() {
//        super.onTokenRefresh();
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        updateTokenToServer(refreshedToken);//when refreshed token, we need to update realtime db
//    }
//
//    private void updateTokenToServer(String refreshedToken) {
//        FirebaseDatabase db =FirebaseDatabase.getInstance();
//        DatabaseReference tokens = db.getReference(Common.token_tbl);
//
//        Token token = new Token(refreshedToken);
//        if (FirebaseAuth.getInstance().getCurrentUser() !=null)//if already login, updated token
//            tokens.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                    .setValue(token);
//
//    }
}
