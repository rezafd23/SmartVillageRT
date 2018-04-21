package com.example.rezafd.smartvillagert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rezafd.smartvillagert.API.APIRequest;
import com.example.rezafd.smartvillagert.API.BaseActivity;
import com.example.rezafd.smartvillagert.API.Retroserver;
import com.example.rezafd.smartvillagert.Model.DataModel;
import com.example.rezafd.smartvillagert.Model.ResponModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AspirasiActivity extends BaseActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText inputaspirasi;
    private Spinner spin_privasi,spin_kategori;
    private ImageView setimage;
    private Button btnaddphoto,btnkirim;
    private Uri mImageUri;

    private String id_warga="";
    private String profilephoto,mprofilephoto,profilelink;;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;
    private List<DataModel>mItem=new ArrayList<>();

    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirasi);
        android.support.v7.widget.Toolbar toolbar_back = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambahkan Aspirasi");

        inputaspirasi=(EditText)findViewById(R.id.inputaspirasi);
        spin_kategori=(Spinner)findViewById(R.id.spin_kategori);
        spin_privasi=(Spinner)findViewById(R.id.spin_privasi);
        setimage=(ImageView)findViewById(R.id.setimage);
//        btnaddphoto=(Button)findViewById(R.id.btnaddphoto);
        btnkirim=(Button)findViewById(R.id.btnkirim);

        id_warga=getSp().getString("id_warga",null);


        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("aspirasi");

        counttabel();

//        btnaddphoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openfilechooser();
//            }
//        });
        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadaspirasi();
            }
        });

    }

    private void uploadaspirasi(){
        final ProgressDialog progressDialog = new ProgressDialog(AspirasiActivity.this,R.style.AppTheme_Dark_Dialog);

        progressDialog.setMessage("Uploading....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIRequest api = Retroserver.getClient().create(APIRequest.class);

        String S_id=id_warga.toString();
        String S_aspirasi = inputaspirasi.getText().toString();
        String S_kategori = spin_kategori.getSelectedItem().toString();
        String S_privasi = spin_privasi.getSelectedItem().toString();
        String S_foto = "aaaaaaa";

        Call<ResponModel> send = api.inputaspirasi(S_id,S_kategori,S_privasi,S_aspirasi,S_foto);
        send.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                progressDialog.dismiss();
                Log.d("Response", response.body().toString());
                int kode = response.body().getKode();
                if (kode == 1) {
                    Toast.makeText(AspirasiActivity.this, "Aspirasi berhasil diupload", Toast.LENGTH_SHORT).show();
                    inputaspirasi.setText("");
                }else if (kode == 124){
                    Toast.makeText(AspirasiActivity.this, "Aspirasi Kosong, Mohon Isi Aspirasi", Toast.LENGTH_SHORT).show();
                } else if (kode == 125){
                    Toast.makeText(AspirasiActivity.this, "Privasi Belum Anda Pilih", Toast.LENGTH_SHORT).show();
                } else if (kode == 126){
                    Toast.makeText(AspirasiActivity.this, "Kategori Belum Anda Pilih", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AspirasiActivity.this, "Periksa Data Anda", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Toast.makeText(AspirasiActivity.this,"Connection Failed",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void counttabel(){
        Call<ResponModel> counttabel = getApi().counttabel();
        counttabel.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                int kode = response.body().getKode();
                if (kode==1){
                    mItem=response.body().getResult();
                    log("Hasilnya"+mItem);
                }
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {

            }
        });
    }
    private void openfilechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void uploadfile() {
        Map userInfo = new HashMap();
//        userInfo.put("id_warga", id_warga);
        mDatabaseRef.updateChildren(userInfo);

        if (mImageUri != null) {
//            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("uploads").child(mnrp);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), mImageUri);

            } catch (IOException e) {
                e.printStackTrace();
            }

                mStorageRef = FirebaseStorage.getInstance().getReference("aspirasi");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,20,baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = mStorageRef.putBytes(data);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        Map newImage = new HashMap();
                        newImage.put("aspirasiImageUrl",downloadUrl.toString());
                        mDatabaseRef.updateChildren(newImage);

                        profilephoto = downloadUrl.toString();


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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(setimage);
//            settingfoto.setImageURI(mImageUri);
        }
    }
}
