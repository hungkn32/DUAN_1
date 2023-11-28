package com.example.duan_1.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.duan_1.Adapter.GiayAdapter;
import com.example.duan_1.Adapter.photoAdapter;
import com.example.duan_1.Adapter.useAdapter;
import com.example.duan_1.Dao.giayDao;
import com.example.duan_1.Model.User;
import com.example.duan_1.Model.giay;
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
    private List<User> mlistuser;
    private Timer timer;
    private RecyclerView rcv;
    private useAdapter adapter;
    GiayAdapter giayAdapter;
    private SearchView searchView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_1, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        rcv = view.findViewById(R.id.rcv_list);
        mlistuser = new ArrayList<>();
        mlistuser.add(new User("30%",R.drawable.giayadidas, "100.000đ"));
        mlistuser.add(new User("40%",R.drawable.bitis, "540.000đ"));
        mlistuser.add(new User("25%",R.drawable.mlb, "240.000đ"));
        mlistuser.add(new User("39%",R.drawable.nike, "299.000đ"));
        mlistuser.add(new User("50%",R.drawable.thuongdinh, "188.000đ"));
        mlistuser.add(new User("45%",R.drawable.balenciaga, "359.000đ"));
        mlistuser.add(new User("29%",R.drawable.nikemag, "999.000đ"));

        adapter = new useAdapter(getContext(),mlistuser);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);


        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle);
        mlistphoto = getlistphoto();
        photoadapter = new photoAdapter(getContext(), mlistphoto);
        viewPager.setAdapter(photoadapter);
        circleIndicator.setViewPager(viewPager);
        photoadapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoslide();

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

    private void autoslide() {
        if (mlistphoto == null || mlistphoto.isEmpty() || viewPager == null) {
            return;
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curentitem = viewPager.getCurrentItem();
                        int totalitem = mlistphoto.size() - 1;
                        if (curentitem < totalitem) {
                            curentitem++;
                            viewPager.setCurrentItem(curentitem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private ArrayList<giay> getlistuer() {
        giayDao dao = new giayDao(getContext());
        ArrayList<giay> list = new ArrayList<>();
        list = dao.getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        giayAdapter = new GiayAdapter(list, getContext());
        rcv.setAdapter(adapter);
        return list;
    }
    }
