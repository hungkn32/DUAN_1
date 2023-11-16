package com.example.duan_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Sign_up extends AppCompatActivity {

    TextInputEditText txt_user,txt_passwword,txt_passwword2,txt_sdt;
    TextInputLayout in_user,in_pass,in_pass2,in_sdt;

    Button btndangki,btntrove;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txt_user = findViewById(R.id.ed_txtUser);
        txt_passwword = findViewById(R.id.ed_txtpassword);
        txt_passwword2 = findViewById(R.id.ed_txtpassword2);
        txt_sdt = findViewById(R.id.ed_txtsdt);

        in_user = findViewById(R.id.in_User1);
        in_pass = findViewById(R.id.in_password);
        in_pass2 = findViewById(R.id.in_password2);
        in_sdt = findViewById(R.id.in_sdt);

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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