package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Model.User;
import com.example.duan_1.R;

import java.util.List;

public class useAdapter extends RecyclerView.Adapter<useAdapter.useViewholer> {
    private Context context;
    private List<User> mlistuser;
    public useAdapter(Context context,List<User> mlistuser){
        this.mlistuser = mlistuser;
        this.context = context;
    }

    @NonNull
    @Override
    public useViewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sanpham,parent,false);
        return new useViewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull useViewholer holder, int position) {
        User user = mlistuser.get(position);
        holder.img.setImageResource(user.getResouceimg());
        holder.txtname.setText(user.getName());
        holder.txt_magiamgia.setText(user.getMagiamgia());
    }

    @Override
    public int getItemCount() {
        if(mlistuser!=null){
            return mlistuser.size();
        }

        return 0;
    }

    public class useViewholer extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txtname,txt_magiamgia;
        public useViewholer(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_user);
            txtname = itemView.findViewById(R.id.txt_gia);
            txt_magiamgia = itemView.findViewById(R.id.txt_magiamgia);
        }
    }
}
