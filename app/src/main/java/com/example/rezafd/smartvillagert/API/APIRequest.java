package com.example.rezafd.smartvillagert.API;

import com.example.rezafd.smartvillagert.Model.ResponModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequest {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponModel> login(@Field("Username") String Username,
                            @Field("Password")String Password);

    @FormUrlEncoded
    @POST("selectotherprofile.php")
    Call<ResponModel> select(@Field("Username") String Username);

    @FormUrlEncoded
    @POST("insertaspirasi.php")
    Call<ResponModel> inputaspirasi(@Field("id_warga") String id_warga,
                                    @Field("Kategori") String Kategori,
                                    @Field("Privasi") String Privasi,
                                    @Field("Aspirasi") String Aspirasi,
                                    @Field("Foto") String Foto);

    @FormUrlEncoded
    @POST("selectmyaspirasi.php")
    Call<ResponModel>viewmypost(@Field("id_warga") String id_warga);

    @FormUrlEncoded
    @POST("updateprofil.php")
    Call<ResponModel> update(@Field("Username") String Username,
                             @Field("Nama") String Nama,
                             @Field("Alamat")String Alamat,
                             @Field("NoHP")String NoHP,
                             @Field("Email")String Email,
                             @Field("Pekerjaan") String Pekerjaan,
                             @Field("RT") String RT,
                             @Field("RW") String RW,
                             @Field("Password") String Password,
                             @Field("Foto") String foto);

    @FormUrlEncoded
    @POST("uploadphoto.php")
    Call<ResponModel> uploadphoto (@Field("Username") String Username,
                                   @Field("foto") String foto);

    @GET("selectallpost.php")
    Call<ResponModel> viewallpost();

    @GET("counttabel.php")
    Call<ResponModel> counttabel();

    @GET("selectrt.php")
    Call<ResponModel> rtaspirasi();

    @FormUrlEncoded
    @POST("deleteaspirasi.php")
    Call<ResponModel>deleteaspirasi(@Field("id_aspirasi") String id_aspirasi);

    @FormUrlEncoded
    @POST("processaspirasi.php")
    Call<ResponModel>processaspirasi(@Field("id_aspirasi") String id_aspirasi);

    @FormUrlEncoded
    @POST("Register.php")
    Call<ResponModel>Registrasi(@Field("NIK") String NIK,
                                @Field("Username") String Username,
                                @Field("Password") String Password,
                                @Field("Nama") String Nama,
                                @Field("Tmptlahir") String Tmptlahir,
                                @Field("Tgllahir") String Tgllahir,
                                @Field("NoHP") String NoHP,
                                @Field("Email") String Email,
                                @Field("Alamat") String Alamat,
                                @Field("RT") String RT,
                                @Field("RW") String RW,
                                @Field("Pekerjaan") String Pekerjaan,
                                @Field("Status") String Status);


}











