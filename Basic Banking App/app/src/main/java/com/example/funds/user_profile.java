package com.example.funds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.funds.databinding.ActivityUserProfileBinding;

public class user_profile extends AppCompatActivity {
    ImageView back;
    Button money;
    ActivityUserProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        back = findViewById(R.id.back3);
        money = findViewById(R.id.transaction);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_profile.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        Intent intent = this.getIntent();
        int amt = 10000;
        long acno = 741369852654L;
        String name = null;
        if (intent != null){
           int image = intent.getIntExtra("image", R.drawable.ashika);
           acno = intent.getLongExtra("acno1", 123);
           amt = intent.getIntExtra("amt", 10000);
           name = intent.getStringExtra("name");

           binding.imgprofile.setImageResource(image);
           binding.actualname.setText(name);
           binding.actualamt.setText(Integer.toString(amt));
           binding.actualno.setText(Long.toString(acno));
        }
        String finalName = name;
        long finalacno = acno;
        int finalamt = amt;
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_profile.this, Transaction_list.class);
                intent.putExtra("name", finalName);
                intent.putExtra("amt", finalamt);
                intent.putExtra("acno", finalacno);
                startActivity(intent);
            }
        });
    }
}