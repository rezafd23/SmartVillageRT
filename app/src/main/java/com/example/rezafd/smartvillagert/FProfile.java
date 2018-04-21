package com.example.rezafd.smartvillagert;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rezafd.smartvillagert.API.SettingSession;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FProfile extends Fragment {

    private String NIK="";
    private String Username="";
    private String Nama="";
    private String Tmptlahir="";
    private String Tgllahir="";
    private String NoHP="";
    private String Email="";
    private String Alamat="";
    private String RT="";
    private String RW="";
    private String Pekerjaan="";
    private String Status="";
    private String profilelink="";

    private TextView infoNIK,infoUsername,infoNama,infoTmptlahir,infoTgllahir,infoNoHP,infoEmail,infoAlamat,infoPekerjaan,infoStatus;

    private Button btnview,btnviewrt;

    private CircleImageView profilephoto;
    public FProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SettingSession session = new SettingSession();
        NIK=session.getPreference(getActivity(),"NIK");
        Username=session.getPreference(getActivity(),"Username");
        Nama=session.getPreference(getActivity(),"Nama");
        Tmptlahir=session.getPreference(getActivity(),"Tmptlahir");
        Tgllahir=session.getPreference(getActivity(),"Tgllahir");
        NoHP=session.getPreference(getActivity(),"NoHP");
        Email=session.getPreference(getActivity(),"Email");
        Alamat=session.getPreference(getActivity(),"Alamat");
        RT=session.getPreference(getActivity(),"RT");
        RW=session.getPreference(getActivity(),"RW");
        Pekerjaan=session.getPreference(getActivity(),"Pekerjaan");
        Status=session.getPreference(getActivity(),"Status");
        profilelink=session.getPreference(getActivity(),"foto");


        View view = inflater.inflate(R.layout.fragment_fprofile,container,false);
        infoNIK=(TextView)view.findViewById(R.id.infoNIK);
        infoUsername=(TextView)view.findViewById(R.id.infoUsername);
        infoNama=(TextView)view.findViewById(R.id.infoName);
        infoTmptlahir=(TextView)view.findViewById(R.id.infotmptlahir);
        infoTgllahir=(TextView)view.findViewById(R.id.infotgllahir);
        infoNoHP=(TextView)view.findViewById(R.id.infoNoHP);
        infoEmail=(TextView)view.findViewById(R.id.infoemail);
        infoAlamat=(TextView)view.findViewById(R.id.infoAlamat);
        infoPekerjaan=(TextView)view.findViewById(R.id.infoPekerjaan);
        infoStatus=(TextView)view.findViewById(R.id.infoStatus);
        profilephoto=(CircleImageView)view.findViewById(R.id.profilefoto);

        btnview=(Button)view.findViewById(R.id.btninfopost);
        btnviewrt=(Button)view.findViewById(R.id.btnviewrt);

        infoNIK.setText(NIK);
        infoUsername.setText(Username);
        infoNama.setText(Nama);
        infoTmptlahir.setText(Tmptlahir);
        infoTgllahir.setText(Tgllahir);
        infoNoHP.setText(NoHP);
        infoEmail.setText(Email);
        infoAlamat.setText(Alamat);
        infoPekerjaan.setText(Pekerjaan);
        infoStatus.setText(Status);

        getuserinfo();
        Log.d("Link foto = ", profilelink);

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ViewMyAspirasi.class);
                startActivity(intent);
            }
        });
        btnviewrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AspirasiRTActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getuserinfo(){
        Glide.with(getContext()).load(profilelink).into(profilephoto);
    }

}
