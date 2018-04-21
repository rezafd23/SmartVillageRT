package com.example.rezafd.smartvillagert;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.smartvillagert.API.APIRequest;
import com.example.rezafd.smartvillagert.API.BaseActivity;
import com.example.rezafd.smartvillagert.API.Retroserver;
import com.example.rezafd.smartvillagert.API.SettingSession;
import com.example.rezafd.smartvillagert.Model.ResponModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private Button btnSignIn;
    private TextView txtRegister;

    private RelativeLayout rootlayout;

    private String mNIK,mUsername,mPassword,mNama,mTmptlahir,mTgllahir,mNoHP,mEmail,mAlamat,mRT,mRW,
    mPekerjaan,mStatus,mflag;
    SettingSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        rootlayout=(RelativeLayout)findViewById(R.id.rootlayout);
        txtRegister=(TextView)findViewById(R.id.txtRegister);

        session = new SettingSession(this);


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showregister();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlogin();
            }
        });
    }

    private void showregister(){
        AlertDialog.Builder dialog  = new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER");

        LayoutInflater inflater  = LayoutInflater.from(this);
        View register = inflater.inflate(R.layout.layout_register,null);

        final MaterialEditText edtNIK = register.findViewById(R.id.edtNIKR);
        final MaterialEditText edtUsername = register.findViewById(R.id.edtUsernameR);
        final MaterialEditText edtPassword = register.findViewById(R.id.edtPasswordR);
        final MaterialEditText edtNama = register.findViewById(R.id.edtNamaLengkapR);
        final MaterialEditText edtTmptlahir = register.findViewById(R.id.edtTmptLahirR);
        final MaterialEditText edtTgllahir = register.findViewById(R.id.edtTglLahirR);
        final MaterialEditText edtNoHP = register.findViewById(R.id.edtNoHPR);
        final MaterialEditText edtEmail = register.findViewById(R.id.edtEmailR);
        final MaterialEditText edtAlamat = register.findViewById(R.id.edtAlamatR);
        final MaterialEditText edtRT = register.findViewById(R.id.edtRTR);
        final MaterialEditText edtRW = register.findViewById(R.id.edtRWR);
        final MaterialEditText edtPekerjaan = register.findViewById(R.id.edtPekerjaanR);

        mNIK=edtNIK.getText().toString();
        mUsername=edtUsername.getText().toString();
        mPassword=edtPassword.getText().toString();
        mNama=edtNama.getText().toString();
        mTmptlahir=edtTmptlahir.getText().toString();
        mTgllahir=edtTgllahir.getText().toString();
        mNoHP=edtNoHP.getText().toString();
        mEmail=edtEmail.getText().toString();
        mAlamat=edtAlamat.getText().toString();
        mRT=edtRT.getText().toString();
        mRW=edtRW.getText().toString();
        mPekerjaan=edtPekerjaan.getText().toString();
        mStatus="RT";
        mflag="1";


        dialog.setView(register);
        dialog.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                final SpotsDialog waitingDialog = new SpotsDialog(LoginActivity.this);
                waitingDialog.show();

                APIRequest api = Retroserver.getClient().create(APIRequest.class);
//                Call<ResponModel>register = api.Registrasi(mNIK,mUsername,mPassword,mNama,mTmptlahir,mTgllahir,mNoHP,mEmail,mAlamat,mRT,mRW,mPekerjaan,mStatus);
                Call<ResponModel>register = api.Registrasi(edtNIK.getText().toString(),edtUsername.getText().toString(),edtPassword.getText().toString(),edtNama.getText().toString(),edtTmptlahir.getText().toString(),edtTgllahir.getText().toString(),edtNoHP.getText().toString(),edtEmail.getText().toString(),edtAlamat.getText().toString(),edtRT.getText().toString(),edtRW.getText().toString(),edtPekerjaan.getText().toString(),mStatus);
                register.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        if (response.isSuccessful()){
                            ResponModel res = response.body();
                            if (res.isSuccess()){
                                waitingDialog.dismiss();
                                Toast.makeText(LoginActivity.this,"Register Berhasil",Toast.LENGTH_SHORT).show();
                            } else {
                                waitingDialog.dismiss();
                                Toast.makeText(LoginActivity.this,"Registrasi Gagal",Toast.LENGTH_SHORT).show();
                                log("Registrasi Gagal");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        t.printStackTrace();
                        waitingDialog.dismiss();
                        log("Gagal");
                        Toast.makeText(LoginActivity.this,"Gagal Mengambil Koneksi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.show();
    }

    private void showlogin(){
        AlertDialog.Builder dialog  = new AlertDialog.Builder(this);
        dialog.setTitle("SIGN IN");

        LayoutInflater inflater  = LayoutInflater.from(this);
        View login = inflater.inflate(R.layout.layout_login,null);

        final MaterialEditText edtUsername = login.findViewById(R.id.edtUsername);
        final MaterialEditText edtPass = login.findViewById(R.id.edtPassword);

        dialog.setView(login);
        dialog.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                btnSignIn.setEnabled(true);

                //check validation
                if (TextUtils.isEmpty(edtUsername.getText().toString())) {
                    Snackbar.make(rootlayout, "Please enter your NRP", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(edtPass.getText().toString())) {
                    Snackbar.make(rootlayout, "Please enter your Password", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if ((edtPass.getText().toString().length() < 6)) {
                    Snackbar.make(rootlayout, "Your password is too short!!", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                final SpotsDialog waitingDialog = new SpotsDialog(LoginActivity.this);
                waitingDialog.show();

                retrofit2.Call<ResponModel> call =getApi().login(edtUsername.getText().toString(),edtPass.getText().toString());
                call.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponModel> call, Response<ResponModel> response) {
                        if (response.isSuccessful()){
                            ResponModel res = response.body();
                            if (res.isSuccess()){
                                Toast.makeText(LoginActivity.this,"Login Berhasil",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                SharedPreferences.Editor editor = getSp().edit();
                                editor.putString("id_warga",res.getId_warga());
                                editor.putString("NIK",res.getNIK());
                                editor.putString("Username",res.getUsername());
                                editor.putString("Password",edtPass.getText().toString());
                                editor.putString("Nama",res.getNama());
                                editor.putString("Tmptlahir",res.getTmptlahir());
                                editor.putString("Tgllahir",res.getTgllahir());
                                editor.putString("NoHP",res.getNoHP());
                                editor.putString("Email",res.getEmail());
                                editor.putString("Alamat",res.getAlamat());
                                editor.putString("RT",res.getRT());
                                editor.putString("RW",res.getRW());
                                editor.putString("Pekerjaan",res.getPekerjaan());
                                editor.putString("Status",res.getStatus());
                                editor.apply();
//                                saveSPBoolean(SP_SUDAH_LOGIN,true);
                                session.saveSPBoolean(SettingSession.SP_SUDAH_LOGIN,true);
                                startActivity(intent);
                                finish();
                                waitingDialog.dismiss();
                            }else {
                                waitingDialog.dismiss();
                                Toast.makeText(LoginActivity.this,"Username/Password Salah",Toast.LENGTH_SHORT).show();
                                log("Login Gagal");
                            }
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponModel> call, Throwable t) {
                        t.printStackTrace();
                        waitingDialog.dismiss();
                        log("Gagal");
                        Toast.makeText(LoginActivity.this,"Gagal Mengambil Koneksi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
