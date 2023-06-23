package com.valen.kwonnect;

import android.os.Parcel;
import android.os.Parcelable;

public class AnggotaModel implements Parcelable {
    private String id;
    private String user_id;
    private String create_date;
    private String modified_date;
    private String email;
    private String nama;
    private String npm;
    private String prodi;
    private String sabuk;
    private String foto;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String berat_badan;
    private String tinggi_badan;
    private String nomor_whatsapp;

    public AnggotaModel() {
    }

    protected AnggotaModel(Parcel in) {
        id = in.readString();
        user_id = in.readString();
        create_date = in.readString();
        modified_date = in.readString();
        email = in.readString();
        nama = in.readString();
        npm = in.readString();
        prodi = in.readString();
        sabuk = in.readString();
        foto = in.readString();
        tempat_lahir = in.readString();
        tanggal_lahir = in.readString();
        berat_badan = in.readString();
        tinggi_badan = in.readString();
        nomor_whatsapp = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(user_id);
        dest.writeString(create_date);
        dest.writeString(modified_date);
        dest.writeString(email);
        dest.writeString(nama);
        dest.writeString(npm);
        dest.writeString(prodi);
        dest.writeString(sabuk);
        dest.writeString(foto);
        dest.writeString(tempat_lahir);
        dest.writeString(tanggal_lahir);
        dest.writeString(berat_badan);
        dest.writeString(tinggi_badan);
        dest.writeString(nomor_whatsapp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AnggotaModel> CREATOR = new Creator<AnggotaModel>() {
        @Override
        public AnggotaModel createFromParcel(Parcel in) {
            return new AnggotaModel(in);
        }

        @Override
        public AnggotaModel[] newArray(int size) {
            return new AnggotaModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getSabuk() {
        return sabuk;
    }

    public void setSabuk(String sabuk) {
        this.sabuk = sabuk;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(String tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public String getNomor_whatsapp() {
        return nomor_whatsapp;
    }

    public void setNomor_whatsapp(String nomor_whatsapp) {
        this.nomor_whatsapp = nomor_whatsapp;
    }
}