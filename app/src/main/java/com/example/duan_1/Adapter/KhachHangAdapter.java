package com.example.duan_1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Dao.KhachHangDao;
import com.example.duan_1.Dao.giayDao;
import com.example.duan_1.Model.khachhang;
import com.example.duan_1.R;
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
                        dao  = new KhachHangDao(context);
                        int check = dao.delete(kh.getMakh());
                        switch (check) {
                            case 1:
                                list.clear();
                                list = dao.getall();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa thành viên thất bại", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Thành viên đang tồn tại phiếu mượn, hiện tại không thể xóa", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
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
    View view = inflater.inflate(R.layout.dialog_update_giay, null);
    builder.setView(view);
    Dialog dialog = builder.create();
    dialog.show();

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
