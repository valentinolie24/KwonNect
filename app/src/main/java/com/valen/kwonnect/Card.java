package com.valen.kwonnect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Card extends RecyclerView.Adapter<Card.ClassViewHolder> {
    private ArrayList<AnggotaModel> dataAnggota;
    private Context ctx;
    private OnItemLongClickListener onItemLongClickListener;
    private Card cardAdapter;

    public Card(ArrayList<AnggotaModel> dataAnggota, Context ctx) {
        this.dataAnggota = dataAnggota;
        this.ctx = ctx;
        this.cardAdapter = this;
    }


    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(ctx).inflate(R.layout.list_anggota, parent, false);
        return new ClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        AnggotaModel anggota = dataAnggota.get(position);
        holder.tvNama.setText(anggota.getNama());
        holder.tvNpm.setText(anggota.getNpm());
        holder.tvProdi.setText(anggota.getProdi());
        holder.tvSabuk.setText(anggota.getSabuk());
        Glide.with(ctx)
                .load(anggota.getFoto())
                .centerCrop()
                .into(holder.ivFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xFoto = anggota.getFoto();
                String xEmail = anggota.getEmail();
                String xNama = anggota.getNama();
                String xNpm = anggota.getNpm();
                String xProdi = anggota.getProdi();
                String xSabuk = anggota.getSabuk();
                String xTempat = anggota.getTempat_lahir();
                String xTanggal = anggota.getTanggal_lahir();
                String xTinggi = anggota.getTinggi_badan();
                String xBerat = anggota.getBerat_badan();
                String xNoWa = anggota.getNomor_whatsapp();

                Intent kirim = new Intent(ctx, Detail.class);
                kirim.putExtra("xFoto", xFoto);
                kirim.putExtra("xEmail", xEmail);
                kirim.putExtra("xNama", xNama);
                kirim.putExtra("xNpm", xNpm);
                kirim.putExtra("xProdi", xProdi);
                kirim.putExtra("xSabuk", xSabuk);
                kirim.putExtra("xTempat", xTempat);
                kirim.putExtra("xTanggal", xTanggal);
                kirim.putExtra("xTinggi", xTinggi);
                kirim.putExtra("xBerat", xBerat);
                kirim.putExtra("xNoWa", xNoWa);

                ctx.startActivity(kirim);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                pesan.setTitle("Perhatian");
                pesan.setMessage("Anda memilih anggota dengan nama " + anggota.getNama());
                pesan.setCancelable(true);

                pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAnggota(anggota.getId());
                    }
                });

                pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent kirim = new Intent(ctx, UpdateAnggota.class);
                        kirim.putExtra("xId", anggota.getId());
                        kirim.putExtra("xFoto", anggota.getFoto());
                        kirim.putExtra("xEmail", anggota.getEmail());
                        kirim.putExtra("xNama", anggota.getNama());
                        kirim.putExtra("xNpm", anggota.getNpm());
                        kirim.putExtra("xProdi", anggota.getProdi());
                        kirim.putExtra("xSabuk", anggota.getSabuk());
                        kirim.putExtra("xTempat", anggota.getTempat_lahir());
                        kirim.putExtra("xTanggal", anggota.getTanggal_lahir());
                        kirim.putExtra("xTinggi", anggota.getTinggi_badan());
                        kirim.putExtra("xBerat", anggota.getBerat_badan());
                        kirim.putExtra("xNoWa", anggota.getNomor_whatsapp());

                        ctx.startActivity(kirim);
                    }
                });

                pesan.show();
                return false;
            }
        });
    }

    private void deleteAnggota(String id) {
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.deleteAnggota(id);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1){
                        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
                        getAllAnggota();
                    } else {
                        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ctx, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(ctx, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int findPositionById(String id) {
        for (int i = 0; i < dataAnggota.size(); i++) {
            if (dataAnggota.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void getAllAnggota() {
        APIService apiService = Utilities.getRetrofit().create(APIService.class);
        Call<ValueData<List<AnggotaModel>>> call = apiService.getAnggota();
        call.enqueue(new Callback<ValueData<List<AnggotaModel>>>() {
            @Override
            public void onResponse(Call<ValueData<List<AnggotaModel>>> call, Response<ValueData<List<AnggotaModel>>> response) {
                if (response.isSuccessful()) {
                    ValueData<List<AnggotaModel>> valueData = response.body();
                    if (valueData != null && valueData.getSuccess() == 1) {
                        dataAnggota.clear();
                        dataAnggota.addAll(valueData.getData());
                        cardAdapter.setData(dataAnggota); // Update data dalam adapter
                        cardAdapter.notifyDataSetChanged();
                        Toast.makeText(ctx, valueData.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ctx, "Failed to get data: " + valueData.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ctx, "Response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<List<AnggotaModel>>> call, Throwable t) {
                Toast.makeText(ctx, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataAnggota.size();
    }

    public void setData(ArrayList<AnggotaModel> data) {
        this.dataAnggota = data;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener {
        void onLongClick(AnggotaModel anggota);

        boolean onItemLongClick(View view, AnggotaModel anggotaModel, int position);
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvNpm, tvProdi, tvSabuk;
        ImageView ivFoto;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNpm = itemView.findViewById(R.id.tv_npm);
            tvProdi = itemView.findViewById(R.id.tv_prodi);
            tvSabuk = itemView.findViewById(R.id.tv_sabuk);
        }
    }
}
