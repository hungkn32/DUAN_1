package com.example.duan_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan_1.Dao.dangnhapDao;
import com.example.duan_1.Model.admin;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Sign_up extends AppCompatActivity {

    TextInputEditText txt_ten, txt_user, txt_passwword, txt_passwword2, txt_sdt;

    Button btndangki, btntrove;

    dangnhapDao dndao;
    admin a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txt_ten = findViewById(R.id.ed_txtname);
        txt_user = findViewById(R.id.ed_user);
        txt_passwword = findViewById(R.id.ed_txtpassword1);
        txt_passwword2 = findViewById(R.id.ed_txtpassword2);
        btndangki = findViewById(R.id.btnsignup);
        btntrove = findViewById(R.id.btn_trove);
        dndao = new dangnhapDao(this);

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = new admin();
                a.setMadn(txt_user.getText().toString());
                a.setMatkhau(txt_passwword.getText().toString());
                a.setTendangnhap(txt_ten.getText().toString());
                if (validate()>0){
                    if (dndao.insert(a) > 0) {
                        Toast.makeText(Sign_up.this, "ĐĂNG KÍ THÀNH CÔNG", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Sign_up.this, "ĐĂNG KÍ THẤT BẠI", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_up.this, Login.class));
                finish();
            }
        });
    }

    public int validate() {
        int check = 1;
        if (txt_user.getText().length() == 0 || txt_ten.getText().length() == 0 || txt_passwword.getText().length() == 0 || txt_passwword2.getText().length() == 0) {
            Toast.makeText(Sign_up.this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = txt_passwword2.getText().toString();
            String repass = txt_passwword.getText().toString();
            if (!pass.equals(repass)) {
                Toast.makeText(Sign_up.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}