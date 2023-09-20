package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView login;
    EditText name;
    EditText email;
    EditText password;
    EditText cpass;
    Button btsignin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        login = findViewById(R.id.login1);
        name = findViewById(R.id.name);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword2);
        cpass = findViewById(R.id.confirmPassword);
        btsignin = findViewById(R.id.button1);
        DB = new DBHelper(this);
        btsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String cpass1 = cpass.getText().toString();
                if(name1.equals("") && email1.equals("")  && password1.equals("") && cpass1.equals(""))
                    Toast.makeText(MainActivity2.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = DB.checkEmail(email1);
                    if(checkuser == true)
                        Toast.makeText(MainActivity2.this, "User already exists", Toast.LENGTH_SHORT).show();
                    else if(checkuser == false) {
                        Boolean insertData = DB.insertData(name1,email1,password1);
                        if(insertData == true){
                            Toast.makeText(MainActivity2.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}