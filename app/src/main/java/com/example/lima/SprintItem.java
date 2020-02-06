package com.example.lima;

public class SprintItem{
    private String mSprint;
    private String mTanggal;
    private String mJamMulai;
    private String mJamAkhir;

    public SprintItem(String sprint_id, String created_at, String jam_mulai, String jam_akhir) {
        mSprint = sprint_id;
        mTanggal = created_at;
        mJamMulai = jam_mulai;
        mJamAkhir = jam_akhir;
    }

    public String getSprint() { return mSprint; }
    public String getTanggal(){
        return mTanggal;
    }
    public String getJamMulai(){
        return mJamMulai;
    }
    public String getJamAkhir(){
        return mJamAkhir;
    }
}
