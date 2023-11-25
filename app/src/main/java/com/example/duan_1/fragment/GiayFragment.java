package com.example.duan_1.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Adapter.GiayAdapter;
import com.example.duan_1.Dao.giayDao;
import com.example.duan_1.Model.giay;
import com.example.duan_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GiayFragment extends Fragment {
    RecyclerView rcvgiay;
    GiayAdapter adapter;
    giayDao dao;
    ArrayList<giay> list = new ArrayList<>();

    FloatingActionButton fltadd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giay,container,false);
        rcvgiay =view.findViewById(R.id.rcvgiay);
        dao = new giayDao(getContext());
        fltadd = view.findViewById(R.id.flt_add_giay);
        list = dao.getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvgiay.setLayoutManager(layoutManager);
        adapter = new GiayAdapter(list,getContext());
        rcvgiay.setAdapter(adapter);
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogaddgiay();
            }


        });
        return view;
    }
    private void dialogaddgiay() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_giay,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edttengiay = view.findViewById(R.id.edttengiay);
        EditText edtloaigiay = view.findViewById(R.id.edtloaigiay);
        EditText edtgiatien = view.findViewById(R.id.edtgiatien);
        EditText edtavata = view.findViewById(R.id.edtavatagiay);
        Button btnadd = view.findViewById(R.id.btnaddgiay);
        Button btnhuy =  view.findViewById(R.id.btnhuyaddgiay);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tengiay = edttengiay.getText().toString();
                String loaigiay = edtloaigiay.getText().toString();
                int giatien  = Integer.parseInt(edtgiatien.getText().toString());
                String urlavata = edtavata.getText().toString();
                boolean check = dao.insert(tengiay,loaigiay,giatien,urlavata);
                    if (check){
                        Toast.makeText(getContext(), "Thêm Giày Thành Công", Toast.LENGTH_SHORT).show();
                        updatedata();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Thêm Giày Thất Bại", Toast.LENGTH_SHORT).show();
                    }
            }
        });

            btnhuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edttengiay.setText("");
                    edtloaigiay.setText("");
                    edtavata.setText("");
                    edtgiatien.setText("");
                }
            });
    }
    public void updatedata(){
        ArrayList<giay> newlist=new ArrayList<>();
        list.clear();
        list.addAll(newlist);
        adapter.notifyDataSetChanged();
    }
}
