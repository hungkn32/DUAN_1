package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Dao.DonHangDao;
import com.example.duan_1.Model.DonHang;
import com.example.duan_1.Model.hoadon;
import com.example.duan_1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHoler> {
    private Context context;
    private ArrayList<DonHang> list;
    DonHangDao dao;

    public DonHangAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
        dao = new DonHangDao(context);
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        DonHang dh = list.get(position);
        holder.madh.setText(String.valueOf(dh.getMaDonHang()));
        holder.tenkh.setText(dh.getTenkh());
        holder.diachi.setText(dh.getDiachi());
        holder.tengiay.setText(dh.getTengiay());
        holder.loaigiay.setText(dh.getLoaigiay());
        holder.ngaymua.setText(dh.getNgayDatHang());
        holder.tongtien.setText(String.valueOf(dh.getTongTien()));
        holder.trangthai.setText(dh.getTrangthai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView madh, tenkh, diachi, tengiay, loaigiay, ngaymua, tongtien, trangthai;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            madh = itemView.findViewById(R.id.txtmadonhang);
            tenkh = itemView.findViewById(R.id.txttenkh);
            diachi = itemView.findViewById(R.id.txtdiachi);
            tengiay = itemView.findViewById(R.id.txtgiay);
            loaigiay = itemView.findViewById(R.id.txtloaigiay);
            ngaymua = itemView.findViewById(R.id.txtngaymua);
            tongtien = itemView.findViewById(R.id.txttongtien);
            trangthai = itemView.findViewById(R.id.txttrangthai);
        }
    }
}
