package com.example.rezafd.smartvillagert;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.rezafd.smartvillagert.API.APIRequest;
import com.example.rezafd.smartvillagert.API.Retroserver;
import com.example.rezafd.smartvillagert.Adapter.AdapterData;
import com.example.rezafd.smartvillagert.Adapter.AdapterDataAspirasi;
import com.example.rezafd.smartvillagert.Model.DataModel;
import com.example.rezafd.smartvillagert.Model.ResponModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FHome extends Fragment {

    private RecyclerView listallpost;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private ScrollView scrollmain;
    private RelativeLayout relativeprofile;
    private List<DataModel> mItem = new ArrayList<>();
    private SwipeRefreshLayout refresh;
    private FloatingActionButton fadd;


    public FHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fhome, container, false);

        listallpost = (RecyclerView) view.findViewById(R.id.listallpost);
        mManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listallpost.setLayoutManager(mManager);
        scrollmain = (ScrollView)view.findViewById(R.id.scrollmain);
        refresh = (SwipeRefreshLayout)view.findViewById(R.id.refresh);
        fadd=(FloatingActionButton)view.findViewById(R.id.fabadd);

        fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AspirasiActivity.class);
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
        return view;
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
                mAdapter = new AdapterDataAspirasi(mItem,getContext());
                listallpost.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                refresh.setRefreshing(false);
                Toast.makeText(getActivity(),"Failed Connection",Toast.LENGTH_SHORT).show();
                Log.d("Response","Failed");
            }
        });
    }

}
