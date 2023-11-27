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
import com.example.duan_1.Model.hoadon;
import com.example.duan_1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHoler> {
    private Context context;
    private ArrayList<hoadon> list;
    DonHangDao dao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public DonHangAdapter(Context context,ArrayList<hoadon> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        hoadon hd = list.get(position);
        holder.madh.setText(String.valueOf(hd.getMadh()));
        holder.tenkh.setText(hd.getTenkh());
        holder.diachi.setText(hd.getDiachi());
        holder.tengiay.setText(hd.getTengiay());
        holder.loaigiay.setText(hd.getLoaigiay());
        holder.giatien.setText(hd.getGiatien());
        holder.ngaymua.setText(sdf.format(hd.getNgaymua()));
        String trangthai ="";
        if(hd.getTrangthai() == 1){
            trangthai = "Đã Thanh Toán";
            holder.trangthai.setTextColor(ContextCompat.getColor(context, R.color.TrangThai));
        }else{
            trangthai = "Chưa Thanh Toán";
        }
        holder.trangthai.setText(trangthai);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView madh,tenkh,diachi,tengiay,loaigiay,giatien,ngaymua,trangthai;
        ImageButton imgdelte;

        public ViewHoler(@NonNull View itemView) {

            super(itemView);
            madh = itemView.findViewById(R.id.txtmadonhang);
            tenkh = itemView.findViewById(R.id.txtmadonhang);
            diachi = itemView.findViewById(R.id.txtmadonhang);
            tengiay = itemView.findViewById(R.id.txtmadonhang);
            loaigiay = itemView.findViewById(R.id.txtmadonhang);
            giatien = itemView.findViewById(R.id.txtmadonhang);
            ngaymua = itemView.findViewById(R.id.txtmadonhang);
            trangthai = itemView.findViewById(R.id.txtmadonhang);
        }
    }
}
