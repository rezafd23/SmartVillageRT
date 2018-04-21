package com.example.rezafd.smartvillagert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.smartvillagert.API.APIRequest;
import com.example.rezafd.smartvillagert.API.Retroserver;
import com.example.rezafd.smartvillagert.Adapter.AdapterDataAspirasi;
import com.example.rezafd.smartvillagert.Model.DataModel;
import com.example.rezafd.smartvillagert.Model.ResponModel;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView mTextMessage;
    private RecyclerView listallpost;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private ScrollView scrollmain;
    private RelativeLayout content;
    private List<DataModel> mItem = new ArrayList<>();
    private SwipeRefreshLayout refresh;
    private FloatingActionButton fabadd;
    private Uri mImageUri;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FHome fHome = new FHome();
                    android.support.v4.app.FragmentTransaction transactions = getSupportFragmentManager().beginTransaction();
                    transactions.replace(R.id.content,fHome);
                    transactions.commit();
                    scrollmain.setVisibility(View.GONE);
                    fabadd.setVisibility(View.GONE);
                    return true;
//                case R.id.navigation_help:
//                    FPanic fPanic = new FPanic();
//                    android.support.v4.app.FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.content,fPanic);
//                    transaction2.commit();
//                    fabadd.setVisibility(View.GONE);
//                    scrollmain.setVisibility(View.GONE);
//                    return true;
                case R.id.navigation_explore:
                    FExplore fExplore = new FExplore();
                    android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content,fExplore);
                    transaction.commit();
                    fabadd.setVisibility(View.GONE);
                    scrollmain.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_profile:
                    FProfile fProfile = new FProfile();
                    android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                    transaction1.replace(R.id.content,fProfile);
                    transaction1.commit();
                    fabadd.setVisibility(View.GONE);
                    scrollmain.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_more:
                    Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listallpost = (RecyclerView) findViewById(R.id.listallpost);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        listallpost.setLayoutManager(mManager);
        scrollmain = (ScrollView)findViewById(R.id.scrollmain);
        content = (RelativeLayout)findViewById(R.id.content);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        fabadd=(FloatingActionButton)findViewById(R.id.fabadd);

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AspirasiActivity.class);
                startActivity(intent);
            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                loadallpost();
                listallpost.removeAllViewsInLayout();

            }
        });
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
                loadallpost();
            }
        });


    }

    private void loadallpost(){
        APIRequest api = Retroserver.getClient().create(APIRequest.class);
        Call<ResponModel> viewallpost =api.viewallpost();
        viewallpost.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                refresh.setRefreshing(false);
                Log.d("Response","Result"+response);
                mItem=response.body().getResult();
                mAdapter = new AdapterDataAspirasi(mItem,MainActivity.this);
                listallpost.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                refresh.setRefreshing(false);
                Toast.makeText(MainActivity.this,"Failed Connection",Toast.LENGTH_SHORT).show();
                Log.d("Response","Failed");
            }
        });
    }

}
