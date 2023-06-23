package com.valen.kwonnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.valen.kwonnect.databinding.ActivityUpdateAnggotaBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAnggota extends AppCompatActivity {
    String yId, yFoto, yEmail, yNama, yNpm, yProdi, ySabuk, yTempat, yTanggal, yTinggi, yBerat, yNoWa;
    String foto, email, nama, npm, prodi, sabuk, tempat, tanggal, tinggi, berat, nowa;
    private ActivityUpdateAnggotaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateAnggotaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent terima = getIntent();
        yId = terima.getStringExtra("xId");
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

        binding.etFoto.setText(yFoto);
        binding.etEmail.setText(yEmail);
        binding.etNama.setText(yNama);
        binding.etNpm.setText(yNpm);
        binding.etProdi.setText(yProdi);
        binding.etSabuk.setText(ySabuk);
        binding.etTempatLahir.setText(yTempat);
        binding.etTanggalLahir.setText(yTanggal);
        binding.etTinggiBadan.setText(yTinggi);
        binding.etBeratBadan.setText(yBerat);
        binding.etNowa.setText(yNoWa);

        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnggota();
            }
        });
    }

    private void updateAnggota() {
        String foto = binding.etFoto.getText().toString();
        String email = binding.etEmail.getText().toString();
        String nama = binding.etNama.getText().toString();
        String npm = binding.etNpm.getText().toString();
        String prodi = binding.etProdi.getText().toString();
        String sabuk = binding.etSabuk.getText().toString();
        String tempat = binding.etTempatLahir.getText().toString();
        String tanggal = binding.etTanggalLahir.getText().toString();
        String tinggi = binding.etTinggiBadan.getText().toString();
        String berat = binding.etBeratBadan.getText().toString();
        String nowa = binding.etNowa.getText().toString();

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
            APIService apiService = Utilities.getRetrofit().create(APIService.class);
            Call<ValueNoData> call = apiService.updateAnggota(yId, foto, email, nama, npm, prodi, sabuk, tempat, tanggal, tinggi, berat, nowa);
            call.enqueue(new Callback<ValueNoData>() {
                @Override
                public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                    if (response.isSuccessful()) {
                        int success = response.body().getSuccess();
                        String message = response.body().getMessage();

                        if (success == 1) {
                            Toast.makeText(UpdateAnggota.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(UpdateAnggota.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UpdateAnggota.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ValueNoData> call, Throwable t) {
                    Toast.makeText(UpdateAnggota.this, "Retrofit Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
