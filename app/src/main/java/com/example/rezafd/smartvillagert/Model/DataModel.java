
package com.example.rezafd.smartvillagert.Model;

public class DataModel {
    private String id_aspirasi,id_warga,Date,Kategori,Privasi,Aspirasi;
    private String Username,Nama,Tmptlahir,Tgllahir,NoHP,Email,Alamat,RT,RW,Pekerjaan,Status,Foto;
    private String total;

    public DataModel(String username, String nama, String tmptlahir, String tgllahir, String noHP, String email, String alamat, String RT, String RW, String pekerjaan, String status, String foto) {
        Username = username;
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
        Foto = foto;
    }

    public DataModel(String id_aspirasi, String id_warga, String date, String kategori, String privasi, String aspirasi, String status) {
        this.id_aspirasi = id_aspirasi;
        this.id_warga = id_warga;
        Date = date;
        Kategori = kategori;
        Privasi = privasi;
        Aspirasi = aspirasi;
        Status = status;
    }

    public DataModel(String id_aspirasi, String date, String kategori, String privasi, String aspirasi, String foto) {
        this.id_aspirasi = id_aspirasi;
        Date = date;
        Kategori = kategori;
        Privasi = privasi;
        Aspirasi = aspirasi;
        Foto = foto;
    }

    public DataModel(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getId_aspirasi() {
        return id_aspirasi;
    }

    public void setId_aspirasi(String id_aspirasi) {
        this.id_aspirasi = id_aspirasi;
    }

    public String getId_warga() {
        return id_warga;
    }

    public void setId_warga(String id_warga) {
        this.id_warga = id_warga;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }

    public String getPrivasi() {
        return Privasi;
    }

    public void setPrivasi(String privasi) {
        Privasi = privasi;
    }

    public String getAspirasi() {
        return Aspirasi;
    }

    public void setAspirasi(String aspirasi) {
        Aspirasi = aspirasi;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
}
