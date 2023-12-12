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

import com.example.duan_1.Adapter.DonHangAdapter;
import com.example.duan_1.Dao.DonHangDao;
import com.example.duan_1.Model.DonHang;
import com.example.duan_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DonHangFragment extends Fragment {
    RecyclerView rcvdonhang;
    FloatingActionButton fltadd;
    ArrayList<DonHang> list = new ArrayList<>();
    DonHangDao dao;
    DonHangAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donhang, container, false);
        rcvdonhang = view.findViewById(R.id.rcvdonhang);
       dao = new DonHangDao(getContext());
       list = dao.getDsDonHang();
       LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
       rcvdonhang.setLayoutManager(layoutManager);
       adapter = new DonHangAdapter(getContext(),list);
       rcvdonhang.setAdapter(adapter);
        return view;

//        fltadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog(0);
//            }
//        });
//        rcvdonhang.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                return false;
//            }
//        });
//        return view;
//    }
//    private void dialog( final int type){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_add_donhang,null);
//        builder.setView(view);
//        Dialog dialog = builder.create();
//        dialog.show();
//        EditText madh  =view.findViewById(R.id.edMadh);
//        Spinner spten = view.findViewById(R.id.sptenkh);
//        Spinner diachi = view.findViewById(R.id.spdiachi);
//        Spinner sptengiay  =view.findViewById(R.id.sptengiay);
//        Spinner sploaigiay  =view.findViewById(R.id.sploaigiay);
//        TextView txtgiatien = view.findViewById(R.id.dhgiatien);
//        TextView txtngay = view.findViewById(R.id.dhNgay);
//        CheckBox chktrangthai = view.findViewById(R.id.chktrangthai);
//        Button  btnadd = view.findViewById(R.id.btnSaveDH);
//        Button btnhuy =view.findViewById(R.id.btnCancelDH);
//
//        txtngay.setText("Ngày Mua: " + sdf.format(new Date()));
//        madh.setEnabled(false);
//
//
//        khdao= new KhachHangDao(getContext());
//        listkh = new ArrayList<khachhang>();
//        listkh= (ArrayList<khachhang>) khdao.getall();
//        khspiner= new KhachHangSpinerAdapter(getContext(),listkh);
//        spten.setAdapter(khspiner);
//        spten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//               tenkh = listkh.get(position).getTenkh();
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        diachi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                dia = listkh.get(position).getDiachi();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        giaydao= new giayDao(getContext());
//        listgiay = new ArrayList<giay>();
//        listgiay= (ArrayList<giay>) giaydao.getAll();
//        giayspiner= new GiaySpinerAdapter(getContext(),listgiay);
//        spten.setAdapter(giayspiner);
//        sptengiay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                tengiay =listgiay.get(position).getTenGiay();
//                giatien = listgiay.get(position).getGiaTien();
//                txtgiatien.setText("Giá Tiền: " + giatien);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        sploaigiay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                loaigiay = listgiay.get(position).getLoaiGiay();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }
}

