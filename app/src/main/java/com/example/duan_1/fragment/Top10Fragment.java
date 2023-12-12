package com.example.duan_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Adapter.GioHangAdapter;
import com.example.duan_1.Adapter.TopAdapter;
import com.example.duan_1.Dao.DonHangDao;
import com.example.duan_1.Dao.GioHangDao;
import com.example.duan_1.Model.top;
import com.example.duan_1.databinding.FragmentGiohangBinding;
import com.example.duan_1.databinding.FragmentTopBinding;

import java.util.ArrayList;

public class Top10Fragment extends Fragment {
    FragmentTopBinding binding;
    DonHangDao dao;
    TopAdapter topAdapter;
    ArrayList<top> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTopBinding.inflate(inflater, container, false);
       View  view  =binding.getRoot();
//        RecyclerView rcv = binding.rcvtop;
//        dao = new DonHangDao(getContext());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rcv.setLayoutManager(layoutManager);
//        list = (ArrayList<top>) dao.getTop();
//        topAdapter = new TopAdapter(list,getContext());
//        rcv.setAdapter(topAdapter);
        return  view;


    }
}
