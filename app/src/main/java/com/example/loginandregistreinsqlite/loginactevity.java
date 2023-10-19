package com.example.loginandregistreinsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactevity extends AppCompatActivity {
    EditText username , password ;
    Button btn_login, btn_registre ;
    Dbactive db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactevity);
        username=findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        btn_login=findViewById(R.id.login1);
        btn_registre=findViewById(R.id.registre1);
        db= new Dbactive(this);
       btn_registre.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(loginactevity.this , MainActivity.class);
               startActivity(intent);
           }
       });
       btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            String user= username.getText().toString();
            String pass= password.getText().toString();
            if (user.equals("")|| pass.equals("")){
                Toast.makeText(loginactevity.this, "please enter all atributes  ", Toast.LENGTH_SHORT).show();
            }else {
                Boolean login =db.checkusername(user,pass);
                if ( login== true ){
                    Toast.makeText(loginactevity.this, " login with sucess :"+user, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginactevity.this, home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(loginactevity.this, "Login wit not sucess :"+user, Toast.LENGTH_SHORT).show();
                }
            }
           }
       });


    }
}