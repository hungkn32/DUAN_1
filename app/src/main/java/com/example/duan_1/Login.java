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
     dangnhapDao dao;
    TextView txtdangki;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đăng nhập");
        setContentView(R.layout.activity_login);
        ed_txtuser = findViewById(R.id.ed_txtUser);
        ed_txtpass = findViewById(R.id.ed_txtPass);
        in_user = findViewById(R.id.in_User);
        in_pass = findViewById(R.id.in_Pass);
        btnlogin = findViewById(R.id.btnlogin);
        chkSave = findViewById(R.id.chksave);
        txtdangki = findViewById(R.id.txtdk);
        dao = new dangnhapDao(this);
        txtdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Sign_up.class));
                finish();
            }
        });
        SharedPreferences pref = getSharedPreferences("User_File",MODE_PRIVATE);
        ed_txtuser.setText(pref.getString("USERNAME",""));
        ed_txtpass.setText(pref.getString("PASSWORD",""));
        chkSave.setChecked(pref.getBoolean("REMEMBER",false));

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    checklogin();

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
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //lưu lại toàn bộ
        edit.commit();
    }

    private void checklogin(){
        user = ed_txtuser.getText().toString();
        pass = ed_txtpass.getText().toString();
        if (user.trim().isEmpty() ||pass.trim().isEmpty()){
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
                if (dao.checkLogin(user,pass)>0){
                    Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(user, pass, chkSave.isChecked());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("madn", user);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
        }
    }


}