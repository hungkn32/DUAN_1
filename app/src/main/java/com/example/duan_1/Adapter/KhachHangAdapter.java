package com.example.duan_1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.duan_1.Dao.KhachHangDao;
import com.example.duan_1.Dao.giayDao;
import com.example.duan_1.Model.giay;
import com.example.duan_1.Model.khachhang;
import com.example.duan_1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHoler> {
    private ArrayList<khachhang> list;
    private Context context;
    KhachHangDao dao;


    public  KhachHangAdapter(ArrayList<khachhang> list,Context context){
        this.list= list;
        this.context = context;
        dao = new KhachHangDao(context);
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        khachhang kh = list.get(position);
        holder.txtmakh.setText(String.valueOf(kh.getMakh()));
        holder.txttenkh.setText(kh.getTenkh());
        holder.txtnamsinh.setText(kh.getNamsinh());
        holder.txtsdt.setText(String.valueOf(kh.getSdt()));
        holder.txtdiachi.setText(kh.getDiachi());
        Picasso.get().load(kh.getUrlkh()).into(holder.imgkh);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Bạn thật sự muốn xóa Khách Hàng này!!");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        khachhang kh = list.get(position);
                        KhachHangDao khdao = new KhachHangDao(context);
                        if (khdao.delete(kh.getMakh())) {
                            list.clear();
                            list.addAll(khdao.getall());
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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogupdate(kh);
                return false;
            }
        });
    }
private  void dialogupdate(khachhang kh){
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_update_khachhang, null);
    builder.setView(view);
    Dialog dialog = builder.create();
    dialog.show();
    TextInputEditText makh = view.findViewById(R.id.ed_updatemakh);
    TextInputEditText tenkh = view.findViewById(R.id.ed_updatetenkh);
    TextInputEditText namsinh = view.findViewById(R.id.ed_updatenamsinh);
    TextInputEditText sdt = view.findViewById(R.id.ed_updatesdt);
    TextInputEditText diachi = view.findViewById(R.id.ed_updatediachi);
    TextInputEditText url =view.findViewById(R.id.ed_updateurlkh);
    Button btnupdate = view.findViewById(R.id.kh_Update);
    Button btnhuy = view.findViewById(R.id.kh_Cancel);

    makh.setText(String.valueOf(kh.getMakh()));
    tenkh.setText(kh.getTenkh());
    namsinh.setText(kh.getNamsinh());
    sdt.setText(String.valueOf(kh.getSdt()));
    diachi.setText(kh.getDiachi());
    url.setText(kh.getUrlkh());
    btnupdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int ma = Integer.parseInt(makh.getText().toString());
            String ten = tenkh.getText().toString();
            String sinh  =namsinh.getText().toString();
            int so = Integer.parseInt(sdt.getText().toString());
            String dia = diachi.getText().toString();
            String avata = url.getText().toString();
            boolean check = dao.update(ma,ten,sinh,so,dia,avata);
            if (check){
                list.clear();
                list = dao.getall();
                notifyDataSetChanged();
                Toast.makeText(context, "Cập nhật Khách Hàng thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }else {
                Toast.makeText(context, "Cập nhật Khách Hàng thất bại", Toast.LENGTH_SHORT).show();
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
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView txtmakh,txttenkh,txtnamsinh,txtsdt,txtdiachi;
        ImageView imgkh;
        ImageButton delete;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtmakh = itemView.findViewById(R.id.txtmakh1);
            txttenkh = itemView.findViewById(R.id.txttenkh1);
            txtnamsinh = itemView.findViewById(R.id.txtngaysinhkh1);
            txtsdt = itemView.findViewById(R.id.txtsdt1);
            txtdiachi = itemView.findViewById(R.id.txtdiachi1);
            imgkh =itemView.findViewById(R.id.imgkh);
            delete = itemView.findViewById(R.id.kh_delte);
        }
    }
}
