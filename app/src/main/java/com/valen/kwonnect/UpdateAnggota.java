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

        if (foto.trim().isEmpty()){
            binding.etFoto.setError("Link foto harus diisi!");
        }
        else if (email.trim().isEmpty()) {
            binding.etEmail.setError("Email harus diisi!");
        }
        else if (nama.trim().isEmpty()) {
            binding.etNama.setError("Nama harus diisi!");
        }
        else if (npm.trim().isEmpty()) {
            binding.etNpm.setError("NPM harus diisi!");
        }
        else if (prodi.trim().isEmpty()) {
            binding.etProdi.setError("Prodi harus diisi!");
        }
        else if (sabuk.trim().isEmpty()) {
            binding.etSabuk.setError("Sabuk harus diisi!");
        }
        else if (tempat.trim().isEmpty()) {
            binding.etTempatLahir.setError("Tempat harus diisi!");
        }
        else if (tanggal.trim().isEmpty()) {
            binding.etTanggalLahir.setError("Tanggal harus diisi!");
        }
        else if (tinggi.trim().isEmpty()) {
            binding.etTinggiBadan.setError("Tinggi harus diisi!");
        }
        else if (berat.trim().isEmpty()) {
            binding.etBeratBadan.setError("Berat harus diisi!");
        }
        else if (nowa.trim().isEmpty()) {
            binding.etNowa.setError("No WhatsApp harus diisi!");
        }
        else {

        }

        
    }
}