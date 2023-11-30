package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.Model.SanPham;
import com.example.duan_1.Model.User;
import com.example.duan_1.Model.giay;
import com.example.duan_1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrangChuAdapter extends RecyclerView.Adapter<TrangChuAdapter.useViewholer> {
    private Context context;
    private ArrayList<giay> mlist;

    public TrangChuAdapter(Context context, ArrayList<giay> mlist) {
        this.mlist = mlist;
        this.context = context;
    }


    private OnAddToCartClickListenerTrangChu mAddToCartClickListener;

    public interface OnAddToCartClickListenerTrangChu {
        void onAddToCartClick(giay g);
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    private OnItemClick mListener;

    public void setOnItemClick(OnItemClick listener) {
        mListener = listener;
    }

    public giay getViTriSp(int position) {
        if (position >= 0 && position < mlist.size()) {
            return mlist.get(position);
        }
        return null;
    }

    public void setOnAddToCartClickListenerTrangChu(OnAddToCartClickListenerTrangChu listener) {
        mAddToCartClickListener = listener;
    }

    @NonNull
    @Override
    public useViewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sanpham, parent, false);
        return new useViewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull useViewholer holder, int position) {
        giay g = mlist.get(position);
        holder.txtname.setText(g.getTenGiay());
        holder.txt_gia.setText(String.valueOf(g.getGiaTien()+ " VND"));
        Picasso.get().load(g.getAvataanh()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
        holder.imgcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAddToCartClickListener != null) {
                    mAddToCartClickListener.onAddToCartClick(mlist.get(holder.getAdapterPosition()));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class useViewholer extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txtname, txt_gia;
        ImageButton imgcart;

        public useViewholer(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_AnhSp_Trang_Chu);
            txtname = itemView.findViewById(R.id.txtten_san_pham);
            txt_gia = itemView.findViewById(R.id.txtgiasp);
            imgcart = itemView.findViewById(R.id.btnmuahang);
        }
    }
}
