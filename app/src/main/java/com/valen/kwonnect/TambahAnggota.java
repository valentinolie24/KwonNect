package com.valen.kwonnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.valen.kwonnect.databinding.ActivityDetailBinding;
import com.valen.kwonnect.databinding.ActivityTambahAnggotaBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahAnggota extends AppCompatActivity {
    String foto, email, nama, npm, prodi, sabuk, tempat_lahir, tanggal_lahir, tinggi_badan, berat_badan, nomor_whatsapp;
    private ActivityTambahAnggotaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_anggota);

        binding = ActivityTambahAnggotaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        foto = binding.etFoto.getText().toString();
        email = binding.etEmail.getText().toString();
        nama = binding.etNama.getText().toString();
        npm = binding.etNpm.getText().toString();
        prodi = binding.etProdi.getText().toString();
        sabuk = binding.etSabuk.getText().toString();
        tempat_lahir = binding.etTempatLahir.getText().toString();
        tanggal_lahir = binding.etTanggalLahir.getText().toString();
        tinggi_badan = binding.etTinggiBadan.getText().toString();
        berat_badan = binding.etBeratBadan.getText().toString();
        nomor_whatsapp = binding.etNowa.getText().toString();

        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foto = binding.etFoto.getText().toString();
                email = binding.etEmail.getText().toString();
                nama = binding.etNama.getText().toString();
                npm = binding.etNpm.getText().toString();
                prodi = binding.etProdi.getText().toString();
                sabuk = binding.etSabuk.getText().toString();
                tempat_lahir = binding.etTempatLahir.getText().toString();
                tanggal_lahir = binding.etTanggalLahir.getText().toString();
                tinggi_badan = binding.etTinggiBadan.getText().toString();
                berat_badan = binding.etBeratBadan.getText().toString();
                nomor_whatsapp = binding.etNowa.getText().toString();

                if (TextUtils.isEmpty(foto)) {
                    binding.etFoto.setError("Link foto harus diisi!");
                } else if (TextUtils.isEmpty(email)) {
                    binding.etEmail.setError("Email harus diisi!");
                } else if (TextUtils.isEmpty(nama)) {
                    binding.etNama.setError("Nama harus diisi!");
                } else if (TextUtils.isEmpty(npm)) {
                    binding.etNpm.setError("NPM harus diisi!");
                } else if (TextUtils.isEmpty(prodi)) {
                    binding.etProdi.setError("Prodi harus diisi!");
                } else if (TextUtils.isEmpty(sabuk)) {
                    binding.etSabuk.setError("Sabuk harus diisi!");
                } else if (TextUtils.isEmpty(tempat_lahir)) {
                    binding.etTempatLahir.setError("Tempat lahir harus diisi!");
                } else if (TextUtils.isEmpty(tanggal_lahir)) {
                    binding.etTanggalLahir.setError("Tanggal lahir harus diisi!");
                } else if (TextUtils.isEmpty(tinggi_badan)) {
                    binding.etTinggiBadan.setError("Tinggi badan harus diisi!");
                } else if (TextUtils.isEmpty(berat_badan)) {
                    binding.etBeratBadan.setError("Berat badan harus diisi!");
                } else if (TextUtils.isEmpty(nomor_whatsapp)) {
                    binding.etNowa.setError("NPM harus diisi!");
                } else {
                    addAnggota(foto, email, nama, npm, prodi, sabuk, tempat_lahir, tanggal_lahir, tinggi_badan, berat_badan, nomor_whatsapp);
                }
            }
        });
    }

    private void addAnggota(String foto, String email, String nama, String npm, String prodi, String sabuk, String tempat_lahir, String tanggal_lahir, String tinggi_badan, String berat_badan, String nomor_whatsapp){
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.addAnggota(foto, email, nama, npm, prodi, sabuk, tempat_lahir, tanggal_lahir, tinggi_badan, berat_badan, nomor_whatsapp);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1){
                        Toast.makeText(TambahAnggota.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(TambahAnggota.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TambahAnggota.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(TambahAnggota.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}