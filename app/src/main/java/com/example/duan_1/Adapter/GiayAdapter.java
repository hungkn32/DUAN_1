package com.example.duan_1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_1.Dao.giayDao;
import com.example.duan_1.Model.giay;
import com.example.duan_1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.ViewHoler> {
    private ArrayList<giay> list;
    private Context context;

    giayDao dao;


    public GiayAdapter(ArrayList<giay> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new giayDao(context);
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giay, parent, false);
        return new ViewHoler(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, @SuppressLint("RecyclerView") int position) {
        giay g = list.get(position);
        holder.txtmagiay.setText(String.valueOf("Mã Giày: " + g.getMagiay()));
        holder.txttengiay.setText("Tên Giày: " + g.getTenGiay());
        holder.txtloaigiay.setText("Loại Giày: " + g.getLoaiGiay());
        holder.txtgiatien.setText(String.valueOf("Giá Tiền: " + g.getGiaTien()));
        holder.txtsoluong.setText(String.valueOf("Số Lượng: "+g.getSoluong()));
        String imagePath = g.getAvataanh();
        if (imagePath != null && !imagePath.isEmpty()) {
            Picasso.get().load(imagePath).into(holder.img);
        } else {
            holder.img.setVisibility(View.GONE);
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogupdate(list.get(position));
                return false;
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Bạn thật sự muốn xóa Giày này!!");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giay g = list.get(position);
                        giayDao spdao = new giayDao(context);
                        if (spdao.delete(g.getMagiay())) {
                            list.clear();
                            list.addAll(spdao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Delete failed!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                notifyDataSetChanged();
                builder.setNegativeButton("Hủy", null);
                builder.create().show();
            }
        });
    }

    private OnCartItemPayListener onCartItemPayListener;

    public interface OnCartItemPayListener {
        void onCartItemPay(int position);
    }

    public void setOnCartItemPayListener(OnCartItemPayListener listener) {
        this.onCartItemPayListener = listener;
    }


    private void dialogupdate(giay g) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_giay, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText ed_txtma = view.findViewById(R.id.ed_updatemagiay);
        TextInputEditText ed_txtten = view.findViewById(R.id.ed_updatetengiay);
        TextInputEditText ed_txtloai = view.findViewById(R.id.ed_updateloaigiay);
        TextInputEditText ed_txtgia = view.findViewById(R.id.ed_updategiatien);
        TextInputEditText ed_txurl = view.findViewById(R.id.ed_updateurl);
        TextInputEditText ed_soluong = view.findViewById(R.id.ed_updatesl);

        Button btnupdategiay = view.findViewById(R.id.giay_Update);
        Button btnhuy = view.findViewById(R.id.giay_Cancel);
        ed_txtma.setText(String.valueOf(g.getMagiay()));
        ed_txtten.setText(g.getTenGiay());
        ed_txtloai.setText(g.getLoaiGiay());
        ed_txtgia.setText(String.valueOf(g.getGiaTien()));
        ed_txurl.setText(g.getAvataanh());
        ed_soluong.setText(String.valueOf(g.getSoluong()));

        btnupdategiay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ma = Integer.parseInt(ed_txtma.getText().toString());
                String ten = ed_txtten.getText().toString();
                String loaigiay = ed_txtloai.getText().toString();
                int giatien = Integer.parseInt(ed_txtgia.getText().toString());
                String url = ed_txurl.getText().toString();
                int soluong = Integer.parseInt(ed_soluong.getText().toString());
                boolean check = dao.update(ma, ten, loaigiay, giatien, url,soluong);
                if (check) {
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật Giày thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật Giày thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }

        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView img;
        ImageButton imgdelete;
        TextView txtmagiay, txttengiay, txtloaigiay, txtgiatien,txtsoluong;

        public ViewHoler(@NonNull View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.avatarImageView);
            txtmagiay = itemView.findViewById(R.id.magiaytexview);
            txttengiay = itemView.findViewById(R.id.tenGiayTextView);
            txtloaigiay = itemView.findViewById(R.id.loaiGiayTextView);
            txtgiatien = itemView.findViewById(R.id.giaTienTextView);
            imgdelete = itemView.findViewById(R.id.imgdeltegiay);
            txtsoluong = itemView.findViewById(R.id.soluongTextView);
        }
    }
}
