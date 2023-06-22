package com.valen.kwonnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.valen.kwonnect.databinding.ActivityDetailBinding;

public class Detail extends AppCompatActivity {
    private String yFoto, yEmail, yNama, yNpm, yProdi, ySabuk, yTempat, yTanggal, yTinggi, yBerat, yNoWa;

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent terima = getIntent();
        yFoto = terima.getStringExtra("xFoto");
        yEmail = terima.getStringExtra("xEmail");
        yNama = terima.getStringExtra("xNama");
        yNpm = terima.getStringExtra("xNpm");
        yProdi = terima.getStringExtra("xProdi");
        ySabuk = terima.getStringExtra("xSabuk");
        yTempat = terima.getStringExtra("xTempat");
        yTanggal = terima.getStringExtra("xTanggal");
        yTinggi = terima.getStringExtra("xTinggi");
        yBerat = terima.getStringExtra("xBerat");
        yNoWa = terima.getStringExtra("xNoWa");

        binding.tvEmail.setText(yEmail);
        binding.tvNama.setText(yNama);
        binding.tvNpm.setText(yNpm);
        binding.tvProdi.setText(yProdi);
        binding.tvSabuk.setText(ySabuk);
        binding.tvTempatLahir.setText(yTempat);
        binding.tvTanggalLahir.setText(yTanggal);
        binding.tvTinggiBadan.setText(yTinggi);
        binding.tvBeratBadan.setText(yBerat);
        binding.tvNoWhatsapp.setText(yNoWa);
        Glide
                .with(Detail.this)
                .load(yFoto)
                .into(binding.ivFoto);

        Toast.makeText(this, "Tanggal Lahir " + ySabuk, Toast.LENGTH_SHORT).show();
    }
}