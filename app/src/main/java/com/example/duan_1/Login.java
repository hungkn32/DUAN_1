package com.example.duan_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_1.Dao.dangnhapDao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    TextInputEditText ed_txtuser, ed_txtpass;
    TextInputLayout in_user, in_pass;
    Button btnlogin;
    CheckBox chkSave;
    private dangnhapDao dao;
    TextView txtdangki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_txtuser = findViewById(R.id.ed_txtUser);
        ed_txtpass = findViewById(R.id.ed_txtPass);
        in_user = findViewById(R.id.in_User);
        in_pass = findViewById(R.id.in_Pass);
        btnlogin = findViewById(R.id.btnlogin);
        chkSave = findViewById(R.id.chksave);
        txtdangki = findViewById(R.id.txtdk);
        txtdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Sign_up.class));
                finish();
            }
        });
        SharedPreferences pref = getSharedPreferences("User_File",MODE_PRIVATE);
        ed_txtuser.setText(pref.getString("tenDangNhap",""));
        ed_txtpass.setText(pref.getString("matkhau",""));
        chkSave.setChecked(pref.getBoolean("Remember",false));

        dao = new dangnhapDao(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = ed_txtuser.getText().toString();
                String pass = ed_txtpass.getText().toString();
                boolean check = dao.checklogin(user,pass);
                if (check){
                    Toast.makeText(Login.this,"Đăng Nhập thành Công ",Toast.LENGTH_SHORT).show();
                    rememberUser(user,pass,chkSave.isChecked());
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else {
                    Toast.makeText(Login.this,"Đăng Nhập Thất Bại Mời  Bạn Nhập lại!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("User_File",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            //xóa trắng dữ liệu trước đó
            edit.clear();
        }else{
            //lưu dữ liệu
            edit.putString("tenDangNhap",u);
            edit.putString("matkhau",p);
            edit.putBoolean("Remember",status);
        }
        //lưu lại toàn bộ
        edit.commit();
    }


}