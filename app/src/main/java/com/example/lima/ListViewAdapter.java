package com.example.lima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<SprintItem> {
    private List<SprintItem> sprintItemList;
    private Context context;

    public ListViewAdapter(List<SprintItem> sprintItemList, Context context){
        super(context, R.layout.list_sprint, sprintItemList);
        this.sprintItemList = sprintItemList;
        this.context = context;
    }

    public View getView(final int sprint, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.list_sprint, null, true);

        TextView textViewSprint = listViewItem.findViewById(R.id.textViewSprint);
        TextView textViewTanggal = listViewItem.findViewById(R.id.textViewTanggal);
        TextView textViewJamMulai = listViewItem.findViewById(R.id.textViewJamMulai);
        TextView textViewJamAkhir = listViewItem.findViewById(R.id.textViewJamAkhir);

        SprintItem sprintItem = sprintItemList.get(sprint);

        textViewSprint.setText(sprintItem.getSprint());
        textViewTanggal.setText(sprintItem.getTanggal());
        textViewJamMulai.setText(sprintItem.getJamMulai());
        textViewJamAkhir.setText(sprintItem.getJamAkhir());

        return listViewItem;
    }
}
