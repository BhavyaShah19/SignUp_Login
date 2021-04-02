package com.example.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user,pass,cpass;
    Button log,reg;
    DataBaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DataBaseHelper(MainActivity.this,DataBaseHelper.DATABASE_NAME,null,1);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        cpass=findViewById(R.id.cpassword);
        log=findViewById(R.id.login);
        reg=findViewById(R.id.signup);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=user.getText().toString();
                String password=pass.getText().toString();
                String cpassword=cpass.getText().toString();

                if(username.equals("") || password.equals("") || cpassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields Required",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(cpassword)) {
                        Boolean checkuser = databaseHelper.CheckUsername(username);
                        if (checkuser == true) {
                            Boolean insert = databaseHelper.Insert(username, password);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                user.setText("");
                                pass.setText("");
                                cpass.setText("");
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Username already taken",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password didn't match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}