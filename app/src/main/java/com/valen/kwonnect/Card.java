package com.valen.kwonnect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Card extends RecyclerView.Adapter<Card.ClassViewHolder>{
    private ArrayList<AnggotaModel> dataAnggota;
    private Context ctx;

    public Card(ArrayList<AnggotaModel> dataAnggota, Context ctx) {
        this.dataAnggota = dataAnggota;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_anggota, parent, false);
        return new ClassViewHolder(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        AnggotaModel anggota = dataAnggota.get(position);
        holder.tvEmail.setText(anggota.getEmail());
        holder.tvNama.setText(anggota.getNama());
        holder.tvNpm.setText(anggota.getNpm());
        holder.tvProdi.setText(anggota.getProdi());
        holder.tvTempatLahir.setText(anggota.getTempat());
        holder.tvTanggalLahir.setText(anggota.getTanggal());
        holder.tvTinggiBadan.setText(anggota.getTinggi());
        holder.tvBeratBadan.setText(anggota.getBerat());
        holder.tvNoWa.setText(anggota.getBerat());
        Glide
                .with(ctx)
                .load(anggota.getFoto())
                .centerCrop()
                .into(holder.ivFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xFoto, xEmail, xNama, xNpm, xProdi, xTempat, xTanggal, xTinggi, xBerat, xNoWa;

                xFoto = anggota.getFoto();
                xEmail = anggota.getEmail();
                xNama = anggota.getNama();
                xNpm = anggota.getNpm();
                xProdi = anggota.getProdi();
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

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmail, tvNama, tvNpm, tvProdi, tvTempatLahir, tvTanggalLahir, tvTinggiBadan, tvBeratBadan, tvNoWa;
        ImageView ivFoto;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNpm = itemView.findViewById(R.id.tv_npm);
            tvProdi = itemView.findViewById(R.id.tv_prodi);
            tvTempatLahir = itemView.findViewById(R.id.tv_tempatLahir);
            tvTanggalLahir = itemView.findViewById(R.id.tv_tanggalLahir);
            tvTinggiBadan = itemView.findViewById(R.id.tv_tinggiBadan);
            tvBeratBadan = itemView.findViewById(R.id.tv_beratBadan);
            tvNoWa = itemView.findViewById(R.id.tv_noWhatsapp);
        }
    }
}
