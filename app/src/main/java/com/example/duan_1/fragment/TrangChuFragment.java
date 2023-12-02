package com.example.duan_1.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.duan_1.Adapter.GiayAdapter;
import com.example.duan_1.Adapter.GioHangAdapter;
import com.example.duan_1.Adapter.photoAdapter;
import com.example.duan_1.Adapter.TrangChuAdapter;
import com.example.duan_1.Dao.GioHangDao;
import com.example.duan_1.Dao.giayDao;
import com.example.duan_1.Model.GioHang;
import com.example.duan_1.Model.SanPham;
import com.example.duan_1.Model.User;
import com.example.duan_1.Model.giay;
import com.example.duan_1.Model.photo;
import com.example.duan_1.R;
import com.example.duan_1.SharedViewModel;
import com.example.duan_1.databinding.DialogChitietSanphamBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class TrangChuFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private photoAdapter photoadapter;
    List<photo> mlistphoto;
    List<User> mlistuser;
    ArrayList<giay> list;
    ArrayList<giay> listdem;
    private Timer timer;
    private RecyclerView rcv;
    TrangChuAdapter adapter;
    GiayAdapter giayAdapter;
    giayDao dao;

    Menu menu;
    private GioHangDao gioHangDao;
    private GioHangAdapter gioHangAdapter;
    SharedViewModel sharedViewModel;
    EditText edtseach;
    boolean hasMatchingProducts = true;
    TextView sanpham;
    private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        rcv = view.findViewById(R.id.rcv_list);
        edtseach = view.findViewById(R.id.edtim_kiem);
        dao = new giayDao(getContext());
        sanpham = view.findViewById(R.id.sanpham);
        list = dao.getAll();
        listdem = dao.getAll();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcv.setLayoutManager(gridLayoutManager);
        adapter = new TrangChuAdapter(getContext(), list);
        rcv.setAdapter(adapter);

        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle);
        mlistphoto = getlistphoto();
        photoadapter = new photoAdapter(getContext(), mlistphoto);
        viewPager.setAdapter(photoadapter);
        circleIndicator.setViewPager(viewPager);
        photoadapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoslide();

        edtseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setVisibility(View.GONE);
                circleIndicator.setVisibility(View.GONE);
            }
        });

        edtseach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase(); // Chuyển đổi sang chữ thường
                if (searchText.isEmpty()) {
                    hasMatchingProducts = true;
                    viewPager.setVisibility(View.VISIBLE);
                    rcv.setVisibility(View.VISIBLE);
                    circleIndicator.setVisibility(View.VISIBLE);
                    sanpham.setText("Sản phẩm ");
                    list.clear();
                    list.addAll(listdem);
                    adapter.notifyDataSetChanged();
                } else {
                    viewPager.setVisibility(View.GONE);
                    circleIndicator.setVisibility(View.GONE);
                    rcv.setVisibility(View.VISIBLE);
                    list.clear();
                    for (giay g : listdem) {
                        if (g.getTenGiay().toLowerCase().contains(searchText)) {
                            list.add(g);
                        }
                    }
                    if (list.isEmpty()) {
                        hasMatchingProducts = false;
                    } else {
                        hasMatchingProducts = true;
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        adapter.notifyDataSetChanged();
        gioHangDao = new GioHangDao(getContext());
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        gioHangAdapter = new GioHangAdapter(getContext(), new ArrayList<>());
        adapter.setOnAddToCartClickListenerTrangChu(new TrangChuAdapter.OnAddToCartClickListenerTrangChu() {
            @Override
            public void onAddToCartClick(giay g) {
                addToCart(g);
                Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
            }

        });
        adapter.setOnItemClick(new TrangChuAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                showDialogChiTietSanPham(adapter.getViTriSp(position));
            }
        });
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


    private void addToCart(giay g) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int mand = sharedPreferences.getInt("mataikhoan", 0);
        if (!sharedViewModel.isProductInCart(g.getMagiay())) {
            sharedViewModel.setMasp(g.getMagiay());
            sharedViewModel.setAddToCartClicked(true);
            sharedViewModel.addProductToCart(g.getMagiay());
//            sharedViewModel.getImagePath().observe(this, new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    Picasso.get().load(s).into();
//                }
//            });
            sharedViewModel.setQuantityToAdd(1);
            gioHangDao.insertGioHang(new GioHang(g.getMagiay(), mand, 1,g.getAvataanh()));
        } else {
            GioHang hang = gioHangDao.getGioHangByMasp(g.getMagiay(), mand);
            if (hang != null) {
                hang.setSoLuongMua(hang.getSoLuongMua() + 1);
                gioHangDao.updateGioHang(hang);
            } else {
                GioHang newCartItem = new GioHang(g.getMagiay(), mand, 1,g.getAvataanh());
                gioHangDao.insertGioHang(newCartItem);
            }

            gioHangAdapter.notifyDataSetChanged();
        }
        ArrayList<GioHang> updatedCartList = gioHangDao.getDSGioHang();
        gioHangAdapter.updateCartList(updatedCartList);
        gioHangAdapter.notifyDataSetChanged();

    }

    private void showDialogChiTietSanPham(giay g) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogChitietSanphamBinding chiTietSanPhamBinding = DialogChitietSanphamBinding.inflate(getLayoutInflater());
        dialog.setContentView(chiTietSanPhamBinding.getRoot());

        if (g != null) {
            chiTietSanPhamBinding.txtMaSanPham.setText("Mã Giày: " + String.valueOf(g.getMagiay()));
            chiTietSanPhamBinding.txtTenSanPham.setText("Tên:" + g.getTenGiay());
            chiTietSanPhamBinding.txtGiaSanPham.setText("Giá: " + String.valueOf(g.getGiaTien()));
            chiTietSanPhamBinding.txtLoaiSanPham.setText("Loại Giày: " + g.getLoaiGiay());
            ImageView img = chiTietSanPhamBinding.imgchitiet;
            Picasso.get().load(g.getAvataanh()).into(img);


        }
        chiTietSanPhamBinding.spchiteietback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        chiTietSanPhamBinding.btnThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(g);
//                Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "Đã cập nhật giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}
