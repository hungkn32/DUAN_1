package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_1.Model.khachhang;
import com.example.duan_1.R;

import java.util.ArrayList;

public class KhachHangSpinerAdapter extends ArrayAdapter<khachhang> {
    private Context context;
    private ArrayList<khachhang> list;

    TextView khtenkh, khdiachi;
    public KhachHangSpinerAdapter(@NonNull Context context,ArrayList<khachhang> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khachhangspiner, null);
        }
        final khachhang item = list.get(position);
        if (item != null) {
            khtenkh = v.findViewById(R.id.khtenkh);
            khtenkh.setText(item.getTenkh());
            khdiachi = v.findViewById(R.id.khdiachi);
            khdiachi.setText(item.getDiachi());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khachhangspiner, null);
        }
        final khachhang item = list.get(position);
        if (item != null) {
            khtenkh = v.findViewById(R.id.khtenkh);
            khtenkh.setText(item.getTenkh());
            khdiachi = v.findViewById(R.id.khdiachi);
            khdiachi.setText(item.getDiachi());
        }
        return v;
    }
}
