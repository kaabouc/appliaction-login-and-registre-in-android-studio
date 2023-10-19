package com.example.loginandregistreinsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText repass, pass , name;
    Button btn_registre ,btn_login ;
    Dbactive db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.username);
        pass= findViewById(R.id.password);
        btn_login = findViewById(R.id.login);
        btn_registre = findViewById(R.id.registre);
        repass = findViewById(R.id.password2);
       db = new Dbactive(this);
        btn_registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String user = name.getText().toString();
            String password = pass.getText().toString();
            String Repassword = repass.getText().toString();
               if ( user.equals("")||password.equals("") || Repassword.equals("")){
                   Toast.makeText(MainActivity.this, "please enter all of the fields ", Toast.LENGTH_SHORT).show();
               }else {
                   if ( password.equals(Repassword)){
                       Boolean checkuser= db.checkusername(user, String.valueOf(pass));
                        if (checkuser == false ) {
                            Boolean insert = db.insertDBenUser(user, password);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "insert " + user + " with success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this ,home.class);
                                 startActivity(intent);
                            }else
                            {
                                Toast.makeText(MainActivity.this, "insert " + user + " nos sucess ", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "exist deja ", Toast.LENGTH_SHORT).show();
                        }

                   }else {
                       Toast.makeText(MainActivity.this, " password not correct not matching !!", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this, loginactevity.class);
              startActivity(intent);
            }
        });
    }
}