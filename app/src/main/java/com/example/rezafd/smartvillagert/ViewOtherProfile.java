package com.example.rezafd.smartvillagert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewOtherProfile extends AppCompatActivity {

    TextView Username,Nama,Tmptlahir,Tgllahir,NoHP,Email,Alamat,Pekerjaan,Status;
    CircleImageView foto;
    private String linkfoto,RT,RW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_other_profile);
        Username=(TextView)findViewById(R.id.infoUsername);
        Nama=(TextView)findViewById(R.id.infoName);
        Tmptlahir=(TextView)findViewById(R.id.infotmptlahir);
        Tgllahir=(TextView)findViewById(R.id.infotgllahir);
        NoHP=(TextView)findViewById(R.id.infoNoHP);
        Email=(TextView)findViewById(R.id.infoemail);
        Alamat=(TextView)findViewById(R.id.infoAlamat);
        Pekerjaan=(TextView)findViewById(R.id.infoPekerjaan);
        Status=(TextView)findViewById(R.id.infoStatus);
        foto=(CircleImageView)findViewById(R.id.profilefoto);

        if (savedInstanceState==null){
            Intent data = getIntent();
            Username.setText(data.getStringExtra("Username"));
            Nama.setText(data.getStringExtra("Nama"));
            Tmptlahir.setText(data.getStringExtra("Tmptlahir"));
            Tgllahir.setText(data.getStringExtra("Tgllahir"));
            NoHP.setText(data.getStringExtra("NoHP"));
            Email.setText(data.getStringExtra("Email"));
            RT=data.getStringExtra("RT");
            RW=data.getStringExtra("RW");
            Alamat.setText(data.getStringExtra("Alamat")+", RT "+RT+", RW "+RW);
            Pekerjaan.setText(data.getStringExtra("Pekerjaan"));
            Status.setText(data.getStringExtra("Status"));
            linkfoto=data.getStringExtra("foto");
        }
        Glide.with(this).load(linkfoto).into(foto);
        Log.d("Link foto orang = ", "linknya "+linkfoto);
    }
}
