package com.valen.kwonnect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Grid extends RecyclerView.Adapter<Grid.ClassViewHolder> {
    private ArrayList<AnggotaModel> dataAnggota;
    private OnItemLongClickListener onItemLongClickListener;
    private Context ctx;
    private Grid gridAdapter;

    public Grid(ArrayList<AnggotaModel> dataAnggota, Context ctx) {
        this.dataAnggota = dataAnggota;
        this.ctx = ctx;
        this.gridAdapter = this;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.grid_anggota, parent, false);
        return new ClassViewHolder(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        AnggotaModel anggota = dataAnggota.get(position);
        Glide.with(ctx)
                .load(anggota.getFoto())
                .into(holder.ivGrid);

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
            @Override
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
                return true; // Mengembalikan true agar event onLongClick tidak disalurkan ke event onClick
            }
        });
    }

    private void deleteAnggota(String id) {
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.deleteAnggota(id);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.isSuccessful()) {
                    ValueNoData value = response.body();
                    if (value != null && value.getSuccess() == 1) {
                        Toast.makeText(ctx, value.getMessage(), Toast.LENGTH_SHORT).show();
                        getAllAnggota();
                    } else {
                        Toast.makeText(ctx, "Failed to delete data: " + value.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ctx, "Response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(ctx, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllAnggota() {
        APIService apiService = Utilities.getRetrofit().create(APIService.class);
        Call<ValueData<List<AnggotaModel>>> call = apiService.getAnggota();
        call.enqueue(new Callback<ValueData<List<AnggotaModel>>>() {
            @Override
            public void onResponse(Call<ValueData<List<AnggotaModel>>> call, Response<ValueData<List<AnggotaModel>>> response) {
                if (response.isSuccessful()) {
                    ValueData<List<AnggotaModel>> value = response.body();
                    if (value != null && value.getSuccess() == 1) {
                        dataAnggota.clear();
                        dataAnggota.addAll(value.getData());
                        gridAdapter.notifyDataSetChanged();
                        Toast.makeText(ctx, value.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ctx, "Failed to get data: " + value.getMessage(), Toast.LENGTH_SHORT).show();
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
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGrid;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGrid = itemView.findViewById(R.id.iv_grid);
        }
    }
}
