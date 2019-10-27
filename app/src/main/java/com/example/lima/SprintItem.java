package com.example.lima;

import java.io.Serializable;

public class SprintItem implements Serializable {
    String tanggal, jam_mulai, jam_akhir;

    public SprintItem(String tanggal, String jam_mulai, String jam_akhir) {
        this.tanggal = tanggal;
        this.jam_mulai = jam_mulai;
        this.jam_akhir = jam_akhir;
    }
    public String getTanggal(){
        return tanggal;
    }
    public String getJamMulai(){
        return jam_mulai;
    }
    public String getJamAkhir(){
        return jam_akhir;
    }
}
