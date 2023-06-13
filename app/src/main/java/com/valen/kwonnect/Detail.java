package com.valen.kwonnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail extends AppCompatActivity {

    private ImageView ivFoto;
    private TextView tvEmail, tvNama, tvNpm, tvProdi, tvTempatLahir, tvTanggalLahir, tvTinggiBadan, tvBeratBadan, tvNoWa;
    private String yFoto, yEmail, yNama, yNpm, yProdi, yTempat, yTanggal, yTinggi, yBerat, yNoWa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivFoto = findViewById(R.id.iv_foto);
        tvEmail = findViewById(R.id.tv_email);
        tvNama = findViewById(R.id.tv_nama);
        tvNpm = findViewById(R.id.tv_npm);
        tvProdi = findViewById(R.id.tv_prodi);
        tvTempatLahir = findViewById(R.id.tv_tempatLahir);
        tvTanggalLahir = findViewById(R.id.tv_tanggalLahir);
        tvTinggiBadan = findViewById(R.id.tv_tinggiBadan);
        tvBeratBadan = findViewById(R.id.tv_beratBadan);
        tvNoWa = findViewById(R.id.tv_noWhatsapp);


        Intent terima = getIntent();
        yFoto = terima.getStringExtra("xFoto");
        yEmail = terima.getStringExtra("xEmail");
        yNama = terima.getStringExtra("xNama");
        yNpm = terima.getStringExtra("xNpm");
        yProdi = terima.getStringExtra("xProdi");
        yTempat = terima.getStringExtra("xTempat");
        yTanggal = terima.getStringExtra("xTanggal");
        yTinggi = terima.getStringExtra("xTinggi");
        yBerat = terima.getStringExtra("xBerat");
        yNoWa = terima.getStringExtra("xNoWa");

        tvEmail.setText(yEmail);
        tvNama.setText(yNama);
        tvNpm.setText(yNpm);
        tvProdi.setText(yProdi);
        tvTempatLahir.setText(yTempat);
        tvTanggalLahir.setText(yTanggal);
        tvTinggiBadan.setText(yTinggi);
        tvBeratBadan.setText(yBerat);
        tvNoWa.setText(yNoWa);
        Glide
                .with(Detail.this)
                .load(yFoto)
                .into(ivFoto);

    }
}