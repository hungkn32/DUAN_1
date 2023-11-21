package com.example.duan_1.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.ViewHoler> {
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
            ImageView img;
            TextView txtmagiay,txttengiay,txtloaigiay,txtgiatien;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
