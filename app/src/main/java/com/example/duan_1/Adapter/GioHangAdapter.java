package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Dao.GioHangDao;
import com.example.duan_1.Model.GioHang;
import com.example.duan_1.databinding.ItemGiohangBinding;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHoler> {
    Context context;
    private ArrayList<GioHang> list;
    GioHangDao dao;
    private TotalPriceListener listener;

    public GioHangAdapter(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
        dao = new GioHangDao(context);
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGiohangBinding binding = ItemGiohangBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHoler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        GioHang gioHang = list.get(position);
        holder.binding.txtgia.setText(String.valueOf(gioHang.getGiaSanPham() * gioHang.getSoLuongMua()));
        holder.binding.txttensp.setText(gioHang.getTenSanPham());
        holder.binding.txtsoluong.setText(String.valueOf(gioHang.getSoLuongMua()));

        holder.binding.chkChonSanPham.setOnCheckedChangeListener((compoundButton, b) -> {
            gioHang.setSelected(b);
            updateTotalPrice();
        });

        holder.binding.btncong.setOnClickListener(view -> {
            if (gioHang.getSoLuongMua() >= 1) {
                gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                dao.updateGioHang(gioHang);
                notifyDataSetChanged();
                updateTotalPrice();
            }
        });
        holder.binding.btntru.setOnClickListener(view -> {
            if (gioHang.getSoLuongMua() > 1) {
                gioHang.setSoLuongMua(gioHang.getSoLuongMua() - 1);
                dao.updateGioHang(gioHang);
                notifyDataSetChanged();
                updateTotalPrice();
            } else {
                removeItem(gioHang);
            }
        });

    }

    public void updateCartList(ArrayList<GioHang> updatedList) {
        list.clear();
        list.addAll(updatedList);
        notifyDataSetChanged();

    }

    private void removeItem(GioHang gioHang) {
        if (dao.deleteGioHang(gioHang)) {
            list.remove(gioHang);
            notifyDataSetChanged();
            updateTotalPrice();
        } else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTotalPrice() {
        if (listener != null) {
            int totalAmount = 0;
            for (GioHang gioHang : list) {
                if (gioHang.isSelected()) {
                    totalAmount += gioHang.getSoLuongMua() * gioHang.getGiaSanPham();
                }
            }
            listener.onTotalPriceUpdated(totalAmount);
        }
    }

    public void setTotalPriceListener(TotalPriceListener listener) {
        this.listener = listener;
    }

    public interface TotalPriceListener {
        void onTotalPriceUpdated(int totalAmount);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ItemGiohangBinding binding;

        public ViewHoler(ItemGiohangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
