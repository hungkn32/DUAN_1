package com.example.duan_1.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Adapter.KhachHangAdapter;
import com.example.duan_1.Dao.KhachHangDao;
import com.example.duan_1.Model.khachhang;
import com.example.duan_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

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
        fltadd = view.findViewById(R.id.flt_add_khachhang);
        khdao = new KhachHangDao(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvkh.setLayoutManager(layoutManager);
        list = khdao.getall();
        khadapter = new KhachHangAdapter(list,getContext());
        rcvkh.setAdapter(khadapter);
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogadd();
            }
        });
        return view;
    }
    private void dialogadd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_khachhang,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText edt_tenkh = view.findViewById(R.id.ed_addtenkh);
        TextInputEditText edt_namsinh = view.findViewById(R.id.ed_addnamsinh);
        TextInputEditText edt_sdt = view.findViewById(R.id.ed_addsdt);
        TextInputEditText edt_diachi  =view.findViewById(R.id.ed_adddiachi);
        TextInputEditText edt_url = view.findViewById(R.id.ed_addurlkh);
        Button btnaddkh = view.findViewById(R.id.kh_add);
        Button btncancer= view.findViewById(R.id.kh_Cancel);

        btnaddkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edt_tenkh.getText().toString();
                String nam = edt_namsinh.getText().toString();
                int so = Integer.parseInt(edt_sdt.getText().toString());
                String diachi  =edt_diachi.getText().toString();
                String url  =edt_url.getText().toString();
                boolean check = khdao.insert(ten,nam,so,diachi,url);
                if (check){
                    loadata();
                    khadapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),"Thêm Khách Hàng Thành Công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


    }
    private void loadata(){
        ArrayList<khachhang>  list = khdao.getall();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvkh.setLayoutManager(layoutManager);
        khadapter = new KhachHangAdapter(list,getContext());
        rcvkh.setAdapter(khadapter);
    }
}
