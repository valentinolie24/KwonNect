package com.valen.kwonnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.valen.kwonnect.databinding.ActivityTambahAnggotaBinding;
import com.valen.kwonnect.databinding.ActivityUpdateAnggotaBinding;

public class UpdateAnggota extends AppCompatActivity {
    String foto, email, nama, npm, prodi, sabuk, tempat, tanggal, tinggi, berat, nowa;
    String yFoto, yEmail, yNama, yNpm, yProdi, ySabuk, yTempat, yTanggal, yTinggi, yBerat, yNoWa;
    private ActivityUpdateAnggotaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_anggota);

        binding = ActivityUpdateAnggotaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        foto = binding.etFoto.getText().toString();
        email = binding.etEmail.getText().toString();
        nama = binding.etNama.getText().toString();
        npm = binding.etNpm.getText().toString();
        prodi = binding.etProdi.getText().toString();
        sabuk = binding.etSabuk.getText().toString();
        tempat = binding.etTempatLahir.getText().toString();
        tanggal = binding.etTanggalLahir.getText().toString();
        tinggi = binding.etTinggiBadan.getText().toString();
        berat = binding.etBeratBadan.getText().toString();
        nowa = binding.etNowa.getText().toString();

    }
}