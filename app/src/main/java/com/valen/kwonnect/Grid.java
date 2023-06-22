package com.valen.kwonnect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Grid extends RecyclerView.Adapter<Grid.ClassViewHolder> {
    private ArrayList<AnggotaModel> dataAnggota;
    private Context ctx;

    public Grid(ArrayList<AnggotaModel> dataAnggota, Context ctx) {
        this.dataAnggota = dataAnggota;
        this.ctx = ctx;
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
        Glide
                .with(ctx)
                .load(anggota.getFoto())
                .into(holder.ivGrid);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xFoto, xEmail, xNama, xNpm, xProdi, xSabuk, xTempat, xTanggal, xTinggi, xBerat, xNoWa;

                xFoto = anggota.getFoto();
                xEmail = anggota.getEmail();
                xNama = anggota.getNama();
                xNpm = anggota.getNpm();
                xProdi = anggota.getProdi();
                xSabuk = anggota.getSabuk();
                xTempat = anggota.getTempat();
                xTanggal = anggota.getTanggal();
                xTinggi = anggota.getTinggi();
                xBerat = anggota.getBerat();
                xNoWa = anggota.getNowa();

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
    }

    @Override
    public int getItemCount() {
        return dataAnggota.size();
    }

    public void setData(ArrayList<AnggotaModel> data) {
        this.dataAnggota = data;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGrid;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGrid = itemView.findViewById(R.id.iv_grid);
        }
    }
}
