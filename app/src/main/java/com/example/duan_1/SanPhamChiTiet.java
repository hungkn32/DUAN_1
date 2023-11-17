package com.example.duan_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SanPhamChiTiet extends AppCompatActivity {

    TextView txttensp,txtgiasp,txtmota;
    Button btnadd;
    Spinner spinner;
    ImageView img;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_chi_tiet);
        ActionToolBar();
        initData();
    }

    private void initData() {

    }

    private void itemview(){
        txttensp = findViewById(R.id.tenspchitiet);
        txtgiasp =findViewById(R.id.giachitiet);
        txtmota = findViewById(R.id.txtmota);
        btnadd = findViewById(R.id.btnaddgio);
        spinner = findViewById(R.id.spinnerchitiet);
        toolbar = findViewById(R.id.tolbar);

    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
    }


}