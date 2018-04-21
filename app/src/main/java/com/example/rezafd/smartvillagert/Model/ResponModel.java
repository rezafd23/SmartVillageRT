package com.example.rezafd.smartvillagert.Model;

import java.util.List;

public class ResponModel {

    private String pesan;
    private int kode;
    private boolean success;
    List<DataModel> result;
    private String id_warga,NIK,Username,Password,Nama,Tmptlahir,Tgllahir,NoHP,Email,Alamat,RT,RW,Pekerjaan,Status,foto;

    public ResponModel(String pesan, int kode, boolean success, List<DataModel> result) {
        this.pesan = pesan;
        this.kode = kode;
        this.success = success;
        this.result = result;
    }

    public ResponModel() {
    }

    public ResponModel(String NIK, String username, String password, String nama, String tmptlahir, String tgllahir, String noHP, String email, String alamat, String RT, String RW, String pekerjaan, String status) {
        this.NIK = NIK;
        Username = username;
        Password = password;
        Nama = nama;
        Tmptlahir = tmptlahir;
        Tgllahir = tgllahir;
        NoHP = noHP;
        Email = email;
        Alamat = alamat;
        this.RT = RT;
        this.RW = RW;
        Pekerjaan = pekerjaan;
        Status = status;
    }

    public ResponModel(int kode, List<DataModel> result, boolean success, String id_warga, String NIK, String username, String password, String nama, String tmptlahir, String tgllahir, String noHP, String email, String alamat, String RT, String RW, String pekerjaan, String status, String foto) {
        this.kode = kode;
        this.result = result;
        this.success = success;
        this.id_warga = id_warga;
        this.NIK = NIK;
        Username = username;
        Password = password;
        Nama = nama;
        Tmptlahir = tmptlahir;
        Tgllahir = tgllahir;
        NoHP = noHP;
        Email = email;
        Alamat = alamat;
        this.RT = RT;
        this.RW = RW;
        Pekerjaan = pekerjaan;
        Status = status;
        this.foto = foto;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public List<DataModel> getResult() {
        return result;
    }

    public void setResult(List<DataModel> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getId_warga() {
        return id_warga;
    }

    public void setId_warga(String id_warga) {
        this.id_warga = id_warga;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getTmptlahir() {
        return Tmptlahir;
    }

    public void setTmptlahir(String tmptlahir) {
        Tmptlahir = tmptlahir;
    }

    public String getTgllahir() {
        return Tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        Tgllahir = tgllahir;
    }

    public String getNoHP() {
        return NoHP;
    }

    public void setNoHP(String noHP) {
        NoHP = noHP;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getRT() {
        return RT;
    }

    public void setRT(String RT) {
        this.RT = RT;
    }

    public String getRW() {
        return RW;
    }

    public void setRW(String RW) {
        this.RW = RW;
    }

    public String getPekerjaan() {
        return Pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        Pekerjaan = pekerjaan;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
