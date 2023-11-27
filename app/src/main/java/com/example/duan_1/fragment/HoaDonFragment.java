package com.example.duan_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Adapter.DonHangAdapter;
import com.example.duan_1.Adapter.GiaySpinerAdapter;
import com.example.duan_1.Adapter.KhachHangSpinerAdapter;
import com.example.duan_1.Dao.DonHangDao;
import com.example.duan_1.Model.hoadon;
import com.example.duan_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HoaDonFragment extends Fragment {
    RecyclerView rcvdonhang;
    FloatingActionButton fltadd;
    ArrayList<hoadon> list = new ArrayList<>();
    DonHangDao dao;
    DonHangAdapter adapter;
    Spinner spten,spdiachi,sptengiay,sploaigiay,spgiatien;
    KhachHangSpinerAdapter khspiner;
    GiaySpinerAdapter giayspiner;
    int maLoaiSach, position;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donhang,container,false);
        rcvdonhang = view.findViewById(R.id.rcvdonhang);
        fltadd = view.findViewById(R.id.flt_add_dh);

        return view;
    }
}
