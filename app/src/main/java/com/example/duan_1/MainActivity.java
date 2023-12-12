package com.example.duan_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.duan_1.Dao.dangnhapDao;
import com.example.duan_1.Model.admin;
import com.example.duan_1.fragment.ChangePassFragment;
import com.example.duan_1.fragment.DoanhThuFragment;
import com.example.duan_1.fragment.GiayFragment;
import com.example.duan_1.fragment.GioHangFragment;
import com.example.duan_1.fragment.DonHangFragment;
import com.example.duan_1.fragment.KhachHangFragment;
import com.example.duan_1.fragment.NhanVienFragment;
import com.example.duan_1.fragment.Top10Fragment;
import com.example.duan_1.fragment.TrangChuFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
     Toolbar toolbar;
     NavigationView navigationView;
    Context context = this;
    TextView tvUser;
    View mHeaderView;
    dangnhapDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navlayout);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new TrangChuFragment()).commit();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);


        mHeaderView = navigationView.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.txt_HeaderTextView);
        Intent i = getIntent();
        String user = i.getStringExtra("madn");
         dao = new dangnhapDao(this);
         admin ad = dao.getID(user);
        String username = ad.getTendangnhap();
        tvUser.setText("Welcome " + username +  "!");

        if (!user.equalsIgnoreCase("admin")&&!user.equalsIgnoreCase("nhanvien")){
            navigationView.getMenu().findItem(R.id.menu_khachhang).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_giay).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_tk_DoanhThu).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_hoadon).setVisible(false);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_trangchu){
                    TrangChuFragment frgtc = new TrangChuFragment();
                    relaceFrg(frgtc);
                    toolbar.setTitle("Trang Chủ");
            } if (item.getItemId() == R.id.menu_hoadon) {
                    DonHangFragment frgPM = new DonHangFragment();
                    relaceFrg(frgPM);
                    toolbar.setTitle("Quản lý Hóa Đơn Đặt Hàng");
                } else if (item.getItemId() == R.id.menu_giay) {
                    GiayFragment frgLS = new GiayFragment();
                    relaceFrg(frgLS);
                    toolbar.setTitle("Quản lý Giày");
                } else if (item.getItemId() == R.id.menu_khachhang) {
                    KhachHangFragment frgTV = new KhachHangFragment();
                    relaceFrg(frgTV);
                    toolbar.setTitle("Quản lý Nhân Viên");
                } else if (item.getItemId() == R.id.menu_tk_top10) {
                    Top10Fragment frgt = new Top10Fragment();
                    relaceFrg(frgt);
                    toolbar.setTitle("Top 10 Giày mượn nhiều nhất");
                } else if (item.getItemId() == R.id.menu_tk_DoanhThu) {
                    DoanhThuFragment frgTND = new DoanhThuFragment();
                    relaceFrg(frgTND);
                    toolbar.setTitle("Top Doanh Thu Cửa Hàng");
                } else if (item.getItemId() == R.id.menu_ressetpass) {
                    ChangePassFragment frgCP = new ChangePassFragment();
                    relaceFrg(frgCP);
                    toolbar.setTitle("Đổi mật khẩu");
                }else if (item.getItemId()==R.id.giohangcart){
                    GioHangFragment frggh = new GioHangFragment();
                    relaceFrg(frggh);
                    toolbar.setTitle("Giỏ Hàng");
                }else if (item.getItemId()==R.id.giohangcart){
                    GioHangFragment frggh = new GioHangFragment();
                    relaceFrg(frggh);
                    toolbar.setTitle("Giỏ Hàng");
                } else if (item.getItemId() == R.id.menu_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn chắc chăn muốn đăng xuất chứ!");
                    builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(MainActivity.this, Login.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("Hủy", null);
                    builder.create().show();
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
//        SharedPreferences sharedPreferences = getSharedPreferences("ADMIN", MODE_PRIVATE);
//        String loaiTaiKhoan = sharedPreferences.getString("madn", "");
//        if (!loaiTaiKhoan.equals("admin")) {
//            Menu menu = navigationView.getMenu();
//            menu.findItem(R.id.menu_tk_DoanhThu).setVisible(false);
//            menu.findItem(R.id.menu_tk_top10).setVisible(false);
//            menu.findItem(R.id.menu_khachhang).setVisible(false);
//            menu.findItem(R.id.menu_nhanvien).setVisible(false);
//        }

    
    }
    public void relaceFrg(Fragment frg){
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.framelayout,frg).commit();
    }

}
