package com.example.duan_1.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.duan_1.Model.giay;
import com.example.duan_1.Model.khachhang;
import com.example.duan_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KhachHangFragment extends Fragment {
    RecyclerView rcvkh;
    FloatingActionButton fltadd;
    KhachHangAdapter khadapter;
    KhachHangDao khdao;
    ArrayList<khachhang> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khachhang, container, false);
        rcvkh = view.findViewById(R.id.rcvkhachhang);
        fltadd = view.findViewById(R.id.flt_add_khachhang);
        khdao = new KhachHangDao(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvkh.setLayoutManager(layoutManager);
        list = khdao.getall();
        khadapter = new KhachHangAdapter(list, getContext());
        rcvkh.setAdapter(khadapter);
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogaddkh();
            }
        });
        return view;
    }

    private void dialogaddkh() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_kh, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edttenkh = view.findViewById(R.id.edt_addtenkh);
        EditText edtnamsinh = view.findViewById(R.id.edt_addnamsinh);
        EditText edtsdt = view.findViewById(R.id.edt_addsdt);
        EditText edtdiachi = view.findViewById(R.id.edt_adddiachi);
        EditText edtavatar = view.findViewById(R.id.edt_add_urlkh);
        Button btnadd = view.findViewById(R.id.kh_add);
        Button btnhuy = view.findViewById(R.id.kh_Cancel);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenkh = edttenkh.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                int sdt = Integer.parseInt(edtsdt.getText().toString());
                String diachi = edtdiachi.getText().toString();
                String urlavata = edtavatar.getText().toString();
                boolean check = khdao.insert(tenkh, namsinh, sdt, diachi, urlavata);
                if (check) {
                    Toast.makeText(getContext(), "Thêm Khách Hàng Thành Công", Toast.LENGTH_SHORT).show();
                    updatedata();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm Khách Hàng Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edttenkh.setText("");
                edtnamsinh.setText("");
                edtsdt.setText("");
                edtdiachi.setText("");
                edtavatar.setText("");

            }
        });
    }

    public void updatedata() {
        ArrayList<khachhang> List = new ArrayList<>();
        list.clear();
        list.addAll(List);
        khadapter.notifyDataSetChanged();

    }
}
