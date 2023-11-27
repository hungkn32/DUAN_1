package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_1.Model.giay;
import com.example.duan_1.R;

import java.util.ArrayList;

public class GiaySpinerAdapter extends ArrayAdapter<giay> {
    private Context context;
    private ArrayList<giay> list;

    TextView gtenGiay, gloaiGiay, ggiatien;

    public GiaySpinerAdapter(@NonNull Context context, ArrayList<giay> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_giayspiner, null);
        }
        final giay item = list.get(position);
        if (item != null) {
            gtenGiay = v.findViewById(R.id.gtengiay);
            gtenGiay.setText(item.getTenGiay());
            gloaiGiay = v.findViewById(R.id.gloaigiay);
            gloaiGiay.setText(item.getLoaiGiay());
            ggiatien = v.findViewById(R.id.ggiatien);
            ggiatien.setText(item.getGiaTien());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_giayspiner, null);
        }
        final giay item = list.get(position);
        if (item != null) {
            gtenGiay = v.findViewById(R.id.gtengiay);
            gtenGiay.setText(item.getTenGiay());
            gloaiGiay = v.findViewById(R.id.gloaigiay);
            gloaiGiay.setText(item.getLoaiGiay());
            ggiatien = v.findViewById(R.id.ggiatien);
            ggiatien.setText(item.getGiaTien());
        }
        return v;
    }
}
