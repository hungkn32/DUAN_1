package com.example.duan_1.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Adapter.GioHangAdapter;
import com.example.duan_1.Dao.DonHangDao;
import com.example.duan_1.Dao.GioHangDao;
import com.example.duan_1.Dao.dangnhapDao;
import com.example.duan_1.Model.DonHang;
import com.example.duan_1.Model.GioHang;
import com.example.duan_1.SharedViewModel;
import com.example.duan_1.databinding.FragmentGiohangBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GioHangFragment extends Fragment implements GioHangAdapter.TotalPriceListener{
    FragmentGiohangBinding binding;
        View view;
        GioHangAdapter gioHangAdapter;
        ArrayList<GioHang> list = new ArrayList<>();
        GioHangDao gioHangDao;
        DonHangDao donHangDao;
     SharedViewModel sharedViewModel;
     ArrayList<DonHang> listdonhang = new ArrayList<>();

    private void displayCart(ArrayList<GioHang> cartList) {
        RecyclerView rcv = binding.rcvGioHang;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        if (gioHangAdapter == null) {
            gioHangAdapter = new GioHangAdapter(getContext(), cartList);
            rcv.setAdapter(gioHangAdapter);
        } else {
            gioHangAdapter.updateCartList(cartList);
            gioHangAdapter.notifyDataSetChanged();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =FragmentGiohangBinding.inflate(inflater, container, false);
        view  =binding.getRoot();
        RecyclerView rcv = binding.rcvGioHang;
        gioHangDao = new GioHangDao(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        list = gioHangDao.getDSGioHang();
        gioHangAdapter = new GioHangAdapter(getContext(), list);
        rcv.setAdapter(gioHangAdapter);



        gioHangAdapter.setTotalPriceListener(this);

        donHangDao = new DonHangDao(getContext());

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMasp().observe(getViewLifecycleOwner(), magiay -> {

            if (isAdded() && isVisible()) {
                if (sharedViewModel.getAddToCartClicked().getValue() != null && sharedViewModel.getAddToCartClicked().getValue()) {
                    updateGioHangByMaSp(magiay);
                    sharedViewModel.setAddToCartClicked(true); // Đặt lại trạng thái


                }
            }
        });
        binding.btnThanhToan.setOnClickListener(view -> {
            int totalAmount = Integer.parseInt(binding.txtTongTienThanhToan.getText().toString());
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("User_File", MODE_PRIVATE);
            int mand = sharedPreferences.getInt("mataikhoan", 0);
            LocalDate currentDate = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String ngayHienTai = currentDate.format(formatter);
            DonHang donHang = new DonHang(mand,ngayHienTai,totalAmount,"Đang giao hàng");
            boolean check = donHangDao.insertDonHang(donHang);
                    if (check){
                        listdonhang.clear();
                        listdonhang.addAll(donHangDao.getDsDonHang());
                        Toast.makeText(getContext(), "Đặt Hàng Thành Công!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Thất bại!", Toast.LENGTH_SHORT).show();
                    }

        });
        list = gioHangDao.getDSGioHang();
        displayCart(list);
        return view;
    }


    public void updateGioHangByMaSp(int magiay) {
        if (magiay > 0) {
            ArrayList<GioHang> updatedCartList = gioHangDao.getDSGioHang();
            displayCart(updatedCartList);
        } else {
        }
    }
    @Override
    public void onTotalPriceUpdated(int totalAmount) {
        if (binding != null && binding.txtTongTienThanhToan != null) {
            binding.txtTongTienThanhToan.setText(String.valueOf(totalAmount));
        }
    }
}
