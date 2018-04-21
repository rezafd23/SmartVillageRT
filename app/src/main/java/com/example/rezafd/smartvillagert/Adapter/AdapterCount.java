package com.example.rezafd.smartvillagert.Adapter;

import android.content.Context;

import com.example.rezafd.smartvillagert.Model.DataModel;

import java.util.List;

public class AdapterCount {

    private List<DataModel> mList;
    private Context ctx;

    public AdapterCount(List<DataModel> mList, Context ctx) {
        this.mList = mList;
        this.ctx = ctx;
    }


}
