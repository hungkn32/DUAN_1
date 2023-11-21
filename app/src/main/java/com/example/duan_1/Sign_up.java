package com.example.duan_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan_1.Dao.dangnhapDao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Sign_up extends AppCompatActivity {

    TextInputEditText txt_tendn,txt_passwword,txt_passwword2,txt_sdt;
    TextInputLayout in_pass,in_pass2,in_sdt;

    Button btndangki,btntrove;

    dangnhapDao dndao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txt_tendn = findViewById(R.id.ed_txtuser);
        txt_passwword = findViewById(R.id.ed_txtpassword1);
        txt_passwword2 = findViewById(R.id.ed_txtpassword2);
        txt_sdt = findViewById(R.id.ed_txtltk);
        btndangki = findViewById(R.id.btnsignup);
        btntrove = findViewById(R.id.btn_trove);
        dndao = new dangnhapDao(this);

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String user = txt_tendn.getText().toString();
                    String pass1 = txt_passwword.getText().toString();
                    String pass2 = txt_passwword2.getText().toString();
                    String loaitk = txt_sdt.getText().toString();
                    if (!pass2.equals(pass1)){
                        Toast.makeText(Sign_up.this,"Nhập 2 mật Khẩu Trùng Nhau",Toast.LENGTH_SHORT).show();
                    }else {
                        boolean check = dndao.Register(user,pass1,loaitk);
                        if (check){
                            Toast.makeText(Sign_up.this,"Đăng Kí Thành Công",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(Sign_up.this,"Đăng Kí Thất Bại",Toast.LENGTH_SHORT).show();
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
}