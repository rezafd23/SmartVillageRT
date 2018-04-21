package com.example.rezafd.smartvillagert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rezafd.smartvillagert.DeleteAspirasi;
import com.example.rezafd.smartvillagert.Model.DataModel;
import com.example.rezafd.smartvillagert.R;

import java.util.List;

public class AdapterMyAspirasi extends RecyclerView.Adapter<AdapterMyAspirasi.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public AdapterMyAspirasi(List<DataModel> mList, Context ctx) {
        this.mList = mList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.listkategori.setText(dm.getKategori());
        holder.listprivasi.setText(dm.getPrivasi());
        holder.listdateaspirasi.setText(dm.getDate());
        holder.listaspirasi.setText(dm.getAspirasi());
        holder.listidaspirasi.setText(dm.getId_aspirasi());
//        listlink=dm.getFoto();
        holder.dm=dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView listprivasi, listidaspirasi, listdateaspirasi, listkategori, listaspirasi;
        ImageView imageaspirasi;
        DataModel dm;

        public HolderData(View view) {
            super(view);
            listprivasi=(TextView)view.findViewById(R.id.listprivasi);
            listidaspirasi=(TextView)view.findViewById(R.id.listidaspirasi);
            listdateaspirasi=(TextView)view.findViewById(R.id.listdate);
            listkategori=(TextView)view.findViewById(R.id.listkategori);
            listaspirasi=(TextView)view.findViewById(R.id.listaspirasi);
//            listjudul=(TextView)view.findViewById(R.id.listjudul);
            imageaspirasi=(ImageView)view.findViewById(R.id.imageaspirasi);
            listidaspirasi.setVisibility(View.INVISIBLE);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, DeleteAspirasi.class);
                    intent.putExtra("id_aspirasi",dm.getId_aspirasi());
                    intent.putExtra("date",dm.getDate());
                    intent.putExtra("kategori",dm.getDate());
                    intent.putExtra("aspirasi",dm.getAspirasi());
                    intent.putExtra("privasi",dm.getPrivasi());
                    ctx.startActivity(intent);
                }
            });
        }
    }
}
