package com.example.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    EditText luser,lpass;
    Button llog,llreg,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataBaseHelper dataBaseHelper=new DataBaseHelper(this,DataBaseHelper.DATABASE_NAME,null,1);
        luser=findViewById(R.id.lusername);
        lpass=findViewById(R.id.lpassword);
        llog=findViewById(R.id.llogin);
        llreg=findViewById(R.id.lsignup);
        logout=findViewById(R.id.logout);

        llreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });

        llog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = luser.getText().toString();
                String password = lpass.getText().toString();

                Boolean checklogin = dataBaseHelper.CheckLogin(username, password);
                if(checklogin == true){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this,"Logged Out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this,MainActivity.class));
                finish();
            }
        });
    }
}