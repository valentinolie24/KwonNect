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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Card extends RecyclerView.Adapter<Card.ClassViewHolder> {
    private ArrayList<AnggotaModel> dataAnggota;
    private Context ctx;


    public Card(ArrayList<AnggotaModel> dataAnggota, Context ctx) {
        this.dataAnggota = dataAnggota;
        this.ctx = ctx;
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
                String xTempat = anggota.getTempat();
                String xTanggal = anggota.getTanggal();
                String xTinggi = anggota.getTinggi();
                String xBerat = anggota.getBerat();
                String xNoWa = anggota.getNowa();

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
                pesan.setMessage("Anda memilihi anggota dengan nama " + holder.tvNama.getText().toString());
                pesan.setCancelable(true);

                pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                // action button ubah
                pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent kirim = new Intent(ctx, UpdateAnggota.class);
                        kirim.putExtra("xEmail", holder.tvEmail.getText().toString());
                        kirim.putExtra("xNama", holder.tvNama.getText().toString());
                        kirim.putExtra("xNpm", holder.tvNpm.getText().toString());
                        kirim.putExtra("xProdi", holder.tvProdi.getText().toString());
                        kirim.putExtra("xSabuk", holder.tvSabuk.getText().toString());
                        kirim.putExtra("xTempat", holder.tvTempatLahir.getText().toString());
                        kirim.putExtra("xTanggal", holder.tvTanggalLahir.getText().toString());
                        kirim.putExtra("xTinggi", holder.tvTinggiBadan.getText().toString());
                        kirim.putExtra("xBerat", holder.tvBeratBadan.getText().toString());
                        kirim.putExtra("xNoWa", holder.tvNoWa.getText().toString());
                    }
                });
                pesan.show();
                return false;
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
        TextView tvEmail, tvNama, tvNpm, tvProdi, tvSabuk, tvTempatLahir, tvTanggalLahir, tvTinggiBadan, tvBeratBadan, tvNoWa;
        ImageView ivFoto;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNpm = itemView.findViewById(R.id.tv_npm);
            tvProdi = itemView.findViewById(R.id.tv_prodi);
            tvSabuk = itemView.findViewById(R.id.tv_sabuk);
            tvTempatLahir = itemView.findViewById(R.id.tv_tempatLahir);
            tvTanggalLahir = itemView.findViewById(R.id.tv_tanggalLahir);
            tvTinggiBadan = itemView.findViewById(R.id.tv_tinggiBadan);
            tvBeratBadan = itemView.findViewById(R.id.tv_beratBadan);
            tvNoWa = itemView.findViewById(R.id.tv_noWhatsapp);
        }
    }
}
