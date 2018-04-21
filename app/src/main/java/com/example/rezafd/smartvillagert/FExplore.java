package com.example.rezafd.smartvillagert;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rezafd.smartvillagert.API.APIRequest;
import com.example.rezafd.smartvillagert.API.Retroserver;
import com.example.rezafd.smartvillagert.API.SettingSession;
import com.example.rezafd.smartvillagert.Adapter.AdapterData;
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
public class FExplore extends Fragment {

    private RecyclerView listprof;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private RelativeLayout relativeprofile;
    private List<DataModel> mItem = new ArrayList<> ();
    private SwipeRefreshLayout refresh;
    private String username="";

    public FExplore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fexplore, container, false);
        listprof = (RecyclerView)view.findViewById(R.id.listother);
        listprof.setLayoutManager(new GridLayoutManager(getContext(),3));
        refresh=(SwipeRefreshLayout)view.findViewById(R.id.refresh);

        SettingSession session = new SettingSession();
        username=session.getPreference(getActivity(),"Username");
        Log.d("Username adalah ", username);
//        Toast.makeText(getActivity(),username,Toast.LENGTH_SHORT).show();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                loadallprofile();
                listprof.removeAllViewsInLayout();
            }
        });
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
                loadallprofile();
            }
        });

        return view;
    }

    private void loadallprofile(){
        APIRequest api = Retroserver.getClient().create(APIRequest.class);
        Call<ResponModel> load = api.select(username);
        load.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                Log.d("Response","Result"+response.body().getResult());
                refresh.setRefreshing(false);
                mItem=response.body().getResult();
                mAdapter=new AdapterData(mItem,getContext());
                listprof.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Log.d("Response","Failed Load Data");
                refresh.setRefreshing(false);
                Toast.makeText(getActivity(),"Failed Load Data",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
