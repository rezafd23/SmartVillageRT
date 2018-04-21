package com.example.rezafd.smartvillagert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rezafd.smartvillagert.API.BaseActivity;
import com.example.rezafd.smartvillagert.API.SettingSession;
import com.example.rezafd.smartvillagert.Model.ResponModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeProfile extends BaseActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText SettingNama, Alamat, RT, RW, NoHP, Email, Pekerjaan, Password;
    private Button btnsettingsave, setphotos;
    private CircleImageView settingfoto;
    private ImageView setfoto;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;

    private String Username, profilephoto,mprofilephoto,profilelink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
//        android.support.v7.widget.Toolbar toolbar_back = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SettingNama = (EditText) findViewById(R.id.settingnama);
        Alamat = (EditText) findViewById(R.id.settingalamat);
        RT = (EditText) findViewById(R.id.settingrt);
        RW = (EditText) findViewById(R.id.settingrw);
        NoHP = (EditText) findViewById(R.id.settingnohp);
        Email = (EditText) findViewById(R.id.settingemail);
        Pekerjaan = (EditText) findViewById(R.id.settingpekerjaan);
        Password = (EditText) findViewById(R.id.settingpassword);

        btnsettingsave = (Button) findViewById(R.id.btnsavesetting);
        setphotos = (Button) findViewById(R.id.setphotos);

        settingfoto = (CircleImageView) findViewById(R.id.settingfoto);
        setfoto = (ImageView) findViewById(R.id.choosephoto);

        Username = getSp().getString("Username", null);
        SettingNama.setText(getSp().getString("Nama", null));
        Alamat.setText(getSp().getString("Alamat", null));
        RT.setText(getSp().getString("RT", null));
        RW.setText(getSp().getString("RW", null));
        NoHP.setText(getSp().getString("NoHP", null));
        Email.setText(getSp().getString("Email", null));
        Pekerjaan.setText(getSp().getString("Pekerjaan", null));
        Password.setText(getSp().getString("Password", null));



        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads").child(Username);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("uploads").child(Username);

        getuserinfo();
        setfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfilechooser();
            }
        });

        setphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadfile();

            }
        });
        btnsettingsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedUserInfo();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(settingfoto);
//            settingfoto.setImageURI(mImageUri);
        }
    }

    private void openfilechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void getuserinfo(){
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()&& dataSnapshot.getChildrenCount()>0){
                    Map<String,Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("profileImageUrl")!=null){
                        mprofilephoto = map.get("profileImageUrl").toString();
                        Glide.with(getApplication()).load(mprofilephoto).into(settingfoto);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log("Load Foto Gagal"+databaseError);
            }
        });
    }

    private void uploadfile() {
        Map userInfo = new HashMap();
        userInfo.put("Username", Username);
        userInfo.put("Nama", SettingNama.getText().toString());
        mDatabaseRef.updateChildren(userInfo);

        if (mImageUri != null) {
//            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("uploads").child(mnrp);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), mImageUri);

            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,20,baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = mStorageRef.putBytes(data);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    Map newImage = new HashMap();
                    newImage.put("profileImageUrl",downloadUrl.toString());
                    mDatabaseRef.updateChildren(newImage);
                    profilephoto = downloadUrl.toString();

                    SharedPreferences.Editor editor = getSp().edit();
                    editor.putString("foto", profilephoto);
                    editor.apply();

                    Call<ResponModel>uploadprofile = getApi().uploadphoto(Username,profilephoto);
                    uploadprofile.enqueue(new Callback<ResponModel>() {
                        @Override
                        public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                            if (response.isSuccessful()){
                                ResponModel res = response.body();
                                if (res.isSuccess()){
                                    log("Upload Foto Success");
                                    Toast.makeText(ChangeProfile.this,"Upload Photo Success",Toast.LENGTH_SHORT).show();
                                } else {
                                    log("Upload Gagal");
                                    Toast.makeText(ChangeProfile.this,"Upload Photo Failed",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                log("Error Connection");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponModel> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                    return;
                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    log("Failed Upload Image");
                }
            });
        }
    }

    private void savedUserInfo(){
        SettingSession session = new SettingSession();
        if (mprofilephoto==null){
//            profilelink = session.getPreference(this,"foto");
            profilelink = getSp().getString("foto",null);
        } else if(mprofilephoto!=null) {
            profilelink=mprofilephoto;
            SharedPreferences.Editor editor =getSp().edit();
            editor.putString("foto",profilelink);
        }
        final SpotsDialog waitingDialog = new SpotsDialog(ChangeProfile.this);
        waitingDialog.show();
        Call<ResponModel>updateprofile=getApi().update(Username,SettingNama.getText().toString(),Alamat.getText().toString(),NoHP.getText().toString(),Email.getText().toString(),Pekerjaan.getText().toString(),RT.getText().toString(),RW.getText().toString(),Password.getText().toString(),profilelink);
        updateprofile.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                if (response.isSuccessful()){
                    ResponModel res = response.body();
                    if (res.isSuccess()){
                        log("Berhasil");
                        SharedPreferences.Editor editor = getSp().edit();
                        editor.putString("Nama",SettingNama.getText().toString());
                        editor.putString("Alamat",Alamat.getText().toString());
                        editor.putString("NoHP",NoHP.getText().toString());
                        editor.putString("Email",Email.getText().toString());
                        editor.putString("Pekerjaan",Pekerjaan.getText().toString());
                        editor.putString("RT",RT.getText().toString());
                        editor.putString("RW",RW.getText().toString());
                        editor.putString("Password",Password.getText().toString());
                        editor.putString("foto",profilelink);
                        editor.apply();
                        waitingDialog.dismiss();
                        Intent intent = new Intent(ChangeProfile.this,SettingActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(ChangeProfile.this,"Profile Berhasil Diupdate",Toast.LENGTH_SHORT).show();
                    } else {
                        log("Gagal");
                        waitingDialog.dismiss();
                        Toast.makeText(ChangeProfile.this,"Profile Gagal Update",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    log("Error Response");
                }
                }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
