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
    private OnItemLongClickListener onItemLongClickListener;

    public Card(ArrayList<AnggotaModel> dataAnggota, Context ctx) {
        this.dataAnggota = dataAnggota;
        this.ctx = ctx;
    }

    public Card() {
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
                pesan.setMessage("Anda memilih anggota dengan nama " + anggota.getNama());
                pesan.setCancelable(true);

                pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle delete action
                    }
                });

                pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent kirim = new Intent(ctx, UpdateAnggota.class);
                        kirim.putExtra("xEmail", anggota.getEmail());
                        kirim.putExtra("xNama", anggota.getNama());
                        kirim.putExtra("xNpm", anggota.getNpm());
                        kirim.putExtra("xProdi", anggota.getProdi());
                        kirim.putExtra("xSabuk", anggota.getSabuk());
                        kirim.putExtra("xTempat", anggota.getTempat());
                        kirim.putExtra("xTanggal", anggota.getTanggal());
                        kirim.putExtra("xTinggi", anggota.getTinggi());
                        kirim.putExtra("xBerat", anggota.getBerat());
                        kirim.putExtra("xNoWa", anggota.getNowa());

                        ctx.startActivity(kirim);
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

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener {
        void onLongClick(AnggotaModel anggota);

        void onItemLongClick(View view, AnggotaModel anggotaModel, int position);
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
