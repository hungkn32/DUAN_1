package com.example.duan_1.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.duan_1.Adapter.photoAdapter;
import com.example.duan_1.Adapter.useAdapter;
import com.example.duan_1.Model.User;
import com.example.duan_1.Model.photo;
import com.example.duan_1.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class TrangChuFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private photoAdapter photoadapter;
    private List<photo> mlistphoto;
    private Timer timer;
private RecyclerView rcv;
private useAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle);
        mlistphoto = getlistphoto();
        photoadapter = new photoAdapter(getContext(), mlistphoto);
        viewPager.setAdapter(photoadapter);
        circleIndicator.setViewPager(viewPager);
        photoadapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoslide();
        rcv = view.findViewById(R.id.rcv_list);
        adapter = new useAdapter(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        rcv.setLayoutManager(layoutManager);
        adapter.setdata(getlistuer());
        return view;
    }

    private List<photo> getlistphoto() {
        List<photo> listphoto = new ArrayList<>();
        listphoto.add(new photo(R.drawable.img_11));
        listphoto.add(new photo(R.drawable.img_12));
        listphoto.add(new photo(R.drawable.img_13));
        listphoto.add(new photo(R.drawable.img_14));
        listphoto.add(new photo(R.drawable.img_15));
        return listphoto;
    }
    private void autoslide(){
        if (mlistphoto == null || mlistphoto.isEmpty() || viewPager == null) {
            return;
        }
        if (timer==null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curentitem = viewPager.getCurrentItem();
                        int totalitem = mlistphoto.size() -1;
                        if (curentitem < totalitem){
                            curentitem++;
                            viewPager.setCurrentItem(curentitem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer !=null){
            timer.cancel();
             timer = null;
        }
    }
    private List<User> getlistuer(){
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.img_11,"User name1"));
        list.add(new User(R.drawable.img_16,"User name"));
        list.add(new User(R.drawable.img_11,"User name2"));

        list.add(new User(R.drawable.img_11,"User name5"));
        list.add(new User(R.drawable.img_14,"User name"));
        list.add(new User(R.drawable.img_11,"User name3"));

        list.add(new User(R.drawable.img_12,"User name7"));
        list.add(new User(R.drawable.img_11,"User name"));
        list.add(new User(R.drawable.img_15,"User name4"));
        return list;
    }
}
