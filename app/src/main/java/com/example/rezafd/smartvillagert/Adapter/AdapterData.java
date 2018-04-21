package com.example.rezafd.smartvillagert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rezafd.smartvillagert.Model.DataModel;
import com.example.rezafd.smartvillagert.R;
import com.example.rezafd.smartvillagert.ViewOtherProfile;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{

    private List<DataModel> mList;
    private Context ctx;
    private String listNama,listNoHP,listTmptlahir,listTgllahir,listAlamat,listRT,listRW,listPekerjaan,listStatus,listEmail,listlink;

    public AdapterData(List<DataModel> mList, Context ctx) {
        this.mList = mList;
        this.ctx = ctx;
    }


    @Override
    public AdapterData.HolderData onCreateViewHolder( ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_explore,parent,false);
        AdapterData.HolderData holderData = new AdapterData.HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder( AdapterData.HolderData holder, int position) {
        DataModel model = mList.get(position);
        holder.listUsername.setText(model.getUsername());
        listNama=model.getNama();
        listTmptlahir=model.getTmptlahir();
        listTgllahir=model.getTgllahir();
        listNoHP=model.getNoHP();
        listEmail=model.getEmail();
        listAlamat=model.getAlamat();
        listRT=model.getRT();
        listRW=model.getRW();
        listPekerjaan=model.getPekerjaan();
        listStatus=model.getStatus();
        listlink=model.getFoto();
        holder.model=model;
        Glide.with(this.ctx).load(listlink).into(holder.listfoto);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView listUsername;
        ImageView listfoto;
        DataModel model;

        public HolderData(View view){
            super(view);
            listUsername=(TextView)view.findViewById(R.id.listUsername);
            listfoto=(ImageView)view.findViewById(R.id.listphoto);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, ViewOtherProfile.class);
                    intent.putExtra("Username",model.getUsername());
                    intent.putExtra("Nama",model.getNama());
                    intent.putExtra("Tmptlahir",model.getTmptlahir());
                    intent.putExtra("Tgllahir",model.getTgllahir());
                    intent.putExtra("NoHP",model.getNoHP());
                    intent.putExtra("Email",model.getEmail());
                    intent.putExtra("Alamat",model.getAlamat());
                    intent.putExtra("RT",model.getRT());
                    intent.putExtra("RW",model.getRW());
                    intent.putExtra("Pekerjaan",model.getPekerjaan());
                    intent.putExtra("Status",model.getStatus());
                    intent.putExtra("foto",model.getFoto());

                    ctx.startActivity(intent);
                }
            });
        }
    }
}
