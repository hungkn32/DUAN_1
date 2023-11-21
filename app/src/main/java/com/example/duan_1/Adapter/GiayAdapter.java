package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_1.Model.giay;
import com.example.duan_1.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.ViewHoler> {
    private ArrayList<giay> list;
    private Context context;
    public GiayAdapter(ArrayList<giay> list,Context context){
        this.list =list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giay,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        giay g = list.get(position);
        Glide.with(context)
                .load(g.getAvataanh())
                .into(holder.img);
        holder.txtmagiay.setText(String.valueOf("Mã Giày: "+g.getMagiay()));
        holder.txttengiay.setText("Tên Giày: "+g.getTenGiay());
        holder.txtloaigiay.setText("Loại Giày: "+g.getLoaiGiay());
        holder.txtgiatien.setText(String.valueOf("Giá Tiền: "+g.getGiaTien()));

    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }

        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
            ImageView img;
            TextView txtmagiay,txttengiay,txtloaigiay,txtgiatien;
        public ViewHoler(@NonNull View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.avatarImageView);
            txtmagiay=itemView.findViewById(R.id.magiaytexview);
            txttengiay=itemView.findViewById(R.id.tenGiayTextView);
            txtloaigiay=itemView.findViewById(R.id.loaiGiayTextView);
            txtgiatien=itemView.findViewById(R.id.giaTienTextView);
        }
    }
}
