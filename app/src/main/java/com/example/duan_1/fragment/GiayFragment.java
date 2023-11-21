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

import com.example.duan_1.Adapter.GiayAdapter;
import com.example.duan_1.Dao.giayDao;
import com.example.duan_1.Model.giay;
import com.example.duan_1.R;

import java.util.ArrayList;

public class GiayFragment extends Fragment {
    RecyclerView rcvgiay;
    GiayAdapter adapter;
    giayDao dao;
    ArrayList<giay> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giay,container,false);
        rcvgiay =view.findViewById(R.id.rcvgiay);
        dao = new giayDao(getContext());
        list = dao.getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvgiay.setLayoutManager(layoutManager);
        adapter = new GiayAdapter(list,getContext());
        rcvgiay.setAdapter(adapter);
        return view;
    }
}
