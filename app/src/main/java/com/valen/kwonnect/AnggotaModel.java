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
    private String tempat;
    private String tanggal;
    private String berat;
    private String tinggi;
    private String nowa;

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
        tempat = in.readString();
        tanggal = in.readString();
        berat = in.readString();
        tinggi = in.readString();
        nowa = in.readString();
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
        dest.writeString(tempat);
        dest.writeString(tanggal);
        dest.writeString(berat);
        dest.writeString(tinggi);
        dest.writeString(nowa);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<AnggotaModel> CREATOR = new Parcelable.Creator<AnggotaModel>() {
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

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getNowa() {
        return nowa;
    }

    public void setNowa(String nowa) {
        this.nowa = nowa;
    }
}