package com.valen.kwonnect;

import java.util.ArrayList;

public class DataAnggota {
        public static String [][] data = new String[][] {
                {"https://upload.wikimedia.org/wikipedia/commons/8/87/Ahmad_Dahlan.jpg",
                        "ahmaddahlan@gmail.com",
                        "Admad Dahlan",
                        "2024240061",
                        "Sistem Informasi",
                        "Palembang",
                        "22 Maret 2000",
                        "175 CM",
                        "68 KG",
                        "0895632272075"},
                {"https://upload.wikimedia.org/wikipedia/commons/3/3f/Ahmad_Yani.jpg",
                        "ahmaddahlan@gmail.com",
                        "Admad Dahlan",
                        "2024240061",
                        "Sistem Informasi",
                        "Palembang",
                        "22 Maret 2000",
                        "175 CM",
                        "68 KG",
                        "0895632272075"},
                {"https://upload.wikimedia.org/wikipedia/commons/e/ed/Bung_Tomo.jpg",
                        "ahmaddahlan@gmail.com",
                        "Admad Dahlan",
                        "2024240061",
                        "Sistem Informasi",
                        "Palembang",
                        "22 Maret 2000",
                        "175 CM",
                        "68 KG",
                        "0895632272075"},
                {"https://upload.wikimedia.org/wikipedia/commons/e/e7/Sudirman.jpg",
                        "ahmaddahlan@gmail.com",
                        "Admad Dahlan",
                        "2024240061",
                        "Sistem Informasi",
                        "Palembang",
                        "22 Maret 2000",
                        "175 CM",
                        "68 KG",
                        "0895632272075"},
        };

        public static ArrayList<AnggotaModel> ambilDataPahlawan(){
            ArrayList<AnggotaModel> dataAnggota = new ArrayList<>();
            for (String[] varData : data){
                AnggotaModel model = new AnggotaModel();
                model.setFoto(varData[0]);
                model.setEmail(varData[1]);
                model.setNama(varData[2]);
                model.setNpm(varData[3]);
                model.setProdi(varData[4]);
                model.setTempat(varData[5]);
                model.setTanggal(varData[6]);
                model.setTinggi(varData[7]);
                model.setBerat(varData[8]);
                model.setNowa(varData[9]);

                dataAnggota.add(model);
            }

            return dataAnggota;
        }
    }