package com.example.lima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SprintAdapter extends RecyclerView.Adapter<SprintAdapter.SprintViewHolder> {
    private Context mContext;
    private ArrayList<SprintItem> mSprintList;

    public SprintAdapter(Context context, ArrayList<SprintItem> sprintList){
        mContext = context;
        mSprintList = sprintList;
    }

    @NonNull
    @Override
    public SprintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.sprint_item, parent, false);
        return new SprintViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SprintViewHolder holder, int position) {
        SprintItem currentItem = mSprintList.get(position);

        String sprint = currentItem.getSprint();
        String tanggal = currentItem.getTanggal();
        String jam_mulai = currentItem.getJamMulai();
        String jam_akhir = currentItem.getJamAkhir();

        holder.mTextViewSprint.setText(sprint);
        holder.mTextViewTanggal.setText(tanggal);
        holder.mTextViewJamMulai.setText(jam_mulai);
        holder.mTextViewJamAkhir.setText(jam_akhir);
    }

    @Override
    public int getItemCount() {
        return mSprintList.size();
    }

    public class SprintViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewSprint;
        public TextView mTextViewTanggal;
        public TextView mTextViewJamMulai;
        public TextView mTextViewJamAkhir;

        public SprintViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewSprint = itemView.findViewById(R.id.textViewSprint);
            mTextViewTanggal = itemView.findViewById(R.id.textViewTanggal);
            mTextViewJamMulai = itemView.findViewById(R.id.textViewJamMulai);
            mTextViewJamAkhir = itemView.findViewById(R.id.textViewJamAkhir);
        }
    }
}
