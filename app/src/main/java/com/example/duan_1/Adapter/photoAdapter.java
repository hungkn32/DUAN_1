package com.example.duan_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.duan_1.Model.photo;
import com.example.duan_1.R;

import java.util.List;

public class photoAdapter extends PagerAdapter {
    private Context context;
    private List<photo> listphoto;

    public photoAdapter(Context context,List<photo> listphoto){
        this.context = context;
        this.listphoto = listphoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view  = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);

        ImageView img =view.findViewById(R.id.img_slide);
        photo pt = listphoto.get(position);
        if (pt !=null){
            Glide.with(context).load(pt.getResourceid()).into(img);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (listphoto!=null){
            return listphoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
