package com.example.duan_1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Dao.KhachHangDao;
import com.example.duan_1.Model.khachhang;
import com.example.duan_1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHoler> {
    private ArrayList<khachhang> list;
    private Context context;


    public  KhachHangAdapter(ArrayList<khachhang> list,Context context){
        this.list= list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        khachhang kh = list.get(position);
        holder.txtmakh.setText(String.valueOf(kh.getMakh()));
        holder.txttenkh.setText(kh.getTenkh());
        holder.txtnamsinh.setText(kh.getNamsinh());
        holder.txtsdt.setText(String.valueOf(kh.getSdt()));
        holder.txtdiachi.setText(kh.getDiachi());
        Picasso.get().load(kh.getUrlkh()).into(holder.imgkh);

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView txtmakh,txttenkh,txtnamsinh,txtsdt,txtdiachi;
        ImageView imgkh;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtmakh = itemView.findViewById(R.id.txtmakh1);
            txttenkh = itemView.findViewById(R.id.txttenkh1);
            txtnamsinh = itemView.findViewById(R.id.txtngaysinhkh1);
            txtsdt = itemView.findViewById(R.id.txtsdt1);
            txtdiachi = itemView.findViewById(R.id.txtdiachi1);
            imgkh =itemView.findViewById(R.id.imgkh);
        }
    }
}
