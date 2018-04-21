package com.example.rezafd.smartvillagert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.smartvillagert.API.BaseActivity;
import com.example.rezafd.smartvillagert.Model.ResponModel;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcessActivity extends BaseActivity {

    private TextView listidaspirasi,listdate,listkategori,listprivasi,listaspirasi;
    private Button btnprocess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        android.support.v7.widget.Toolbar toolbar_back = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listidaspirasi=(TextView) findViewById(R.id.listidaspirasid);
        listdate=(TextView)findViewById(R.id.listdated);
        listkategori=(TextView)findViewById(R.id.listkategorid);
        listaspirasi=(TextView)findViewById(R.id.listaspirasid);
        listprivasi=(TextView)findViewById(R.id.listprivasid);
        btnprocess=(Button)findViewById(R.id.btnprocess);

        if (savedInstanceState==null) {
            Intent data = getIntent();
            listidaspirasi.setText(data.getStringExtra("id_aspirasi"));
            listdate.setText(data.getStringExtra("date"));
            listkategori.setText(data.getStringExtra("kategori"));
            listprivasi.setText(data.getStringExtra("privasi"));
            listaspirasi.setText(data.getStringExtra("aspirasi"));
        }

        btnprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SpotsDialog waitingDialog = new SpotsDialog(ProcessActivity.this);
                waitingDialog.show();
                final String id = listidaspirasi.getText().toString();
                Call<ResponModel>process = getApi().processaspirasi(id);
                process.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        if (response.isSuccessful()){
                            ResponModel res = response.body();
                            if (res.isSuccess()){
                                log("Berhasil");
                                waitingDialog.dismiss();
                                Intent intent = new Intent(ProcessActivity.this,AspirasiRTActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(ProcessActivity.this,"Aspirasi Berhasil Diproses",Toast.LENGTH_SHORT).show();
                            } else {
                                log("Gagal");
                                waitingDialog.dismiss();
                                Toast.makeText(ProcessActivity.this,"Aspirasi Gagal Diproses",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            waitingDialog.dismiss();
//                            log("gagal");
                            Toast.makeText(ProcessActivity.this,"GAGAL",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        waitingDialog.dismiss();
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}
