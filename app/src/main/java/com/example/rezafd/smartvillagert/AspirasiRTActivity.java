package com.example.rezafd.smartvillagert;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rezafd.smartvillagert.API.BaseActivity;
import com.example.rezafd.smartvillagert.Adapter.AdapterMyAspirasi;
import com.example.rezafd.smartvillagert.Adapter.AdapterRT;
import com.example.rezafd.smartvillagert.Model.DataModel;
import com.example.rezafd.smartvillagert.Model.ResponModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AspirasiRTActivity extends BaseActivity {

    private RecyclerView mylistpost;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItem = new ArrayList<>();
    private SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirasi_rt);

        android.support.v7.widget.Toolbar toolbar_back = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_backv);
        setSupportActionBar(toolbar_back);
        toolbar_back.setTitle("Aspirasi RT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        refresh =(SwipeRefreshLayout) findViewById(R.id.refresh);
        mylistpost = (RecyclerView) findViewById(R.id.mylistpost);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mylistpost.setLayoutManager(mManager);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                loadaspirasirt();
                mylistpost.removeAllViewsInLayout();
            }
        });
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
                loadaspirasirt();
            }
        });
    }

    private void loadaspirasirt(){
//        String m_idwarga = getSp().getString("id_warga",null);
        String m_idwarga = getSp().getString("id_warga",null);
        Call<ResponModel>viewmypost = getApi().rtaspirasi();
        viewmypost.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                refresh.setRefreshing(false);
                Log.d("RESPON","Hasil"+response.body());
                mItem= response.body().getResult();
                mAdapter = new AdapterRT(mItem,AspirasiRTActivity.this);
                mylistpost.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                refresh.setRefreshing(false);
                Log.d("RESPON","GAGAL");
            }
        });
    }
}
