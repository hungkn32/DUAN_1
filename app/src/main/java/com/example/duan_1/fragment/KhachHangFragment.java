package com.example.duan_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Adapter.KhachHangAdapter;
import com.example.duan_1.Dao.KhachHangDao;
import com.example.duan_1.Model.khachhang;
import com.example.duan_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KhachHangFragment extends Fragment {
    RecyclerView rcvkh;
    FloatingActionButton fltadd;
    KhachHangAdapter khadapter;
    KhachHangDao khdao;
    ArrayList<khachhang> list =new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khachhang,container,false);
        rcvkh = view.findViewById(R.id.rcvkhachhang);
        khdao = new KhachHangDao(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvkh.setLayoutManager(layoutManager);
        list = khdao.getall();
        khadapter = new KhachHangAdapter(list,getContext());
        rcvkh.setAdapter(khadapter);
        return view;
    }
}
