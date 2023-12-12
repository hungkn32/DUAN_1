package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Model.top;
import com.example.duan_1.databinding.FragmentTopBinding;
import com.example.duan_1.databinding.ItemTopBinding;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.Viewholer>{

    ArrayList<top> list;
    Context context;
    public TopAdapter(ArrayList<top> list,Context context){
        this.list = list;
        this.context  =context;

    }

    @NonNull
    @Override
    public Viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTopBinding binding = ItemTopBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Viewholer(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholer holder, int position) {
            top t = list.get(position);
            holder.binding.topgiay.setText("Tên Giày"+t.getTengiay());
            holder.binding.topSL.setText("Số Lượng"+t.getSoluong());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholer extends RecyclerView.ViewHolder{
        ItemTopBinding binding;
        public Viewholer(ItemTopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
