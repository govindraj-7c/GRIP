package com.example.funds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funds.databinding.ActivityUserProfileBinding;

public class Actual_transactions extends AppCompatActivity {
    ImageView back, profile;
    EditText money;
    TextView payname, no;
    Button transactions;
    ActivityUserProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_transactions);
        back = findViewById(R.id.back3);
        profile = findViewById(R.id.profilephoto);
        money = findViewById(R.id.Rupee);
        payname = findViewById(R.id.payername);
        no = findViewById(R.id.payeracc);
        transactions = findViewById(R.id.transaction);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Actual_transactions.this, Transaction_list.class);
                startActivity(intent);
            }
        });
        Intent intent = this.getIntent();
        long acno3 = 741369852654L;
        int amt3 = 10000;
        String name3 = null;
        long acno2 = 741369852654L;
        int amt2 = 5000;
        String name2 = null;
        if (intent != null){
            int image2 = intent.getIntExtra("image", R.drawable.ashika);
            acno2= intent.getLongExtra("acno1", 123);
            name2 = intent.getStringExtra("name");
            amt2 = intent.getIntExtra("amt1",10000);
            acno3 = intent.getLongExtra("acno3", 741369852654L);
            amt3 = intent.getIntExtra("amt3", 10000);
            name3 = intent.getStringExtra("name3");
            profile.setImageResource(image2);
            payname.setText(name2);
            no.setText(Long.toString(acno2));
        }
        int amtMoney = amt3;
        long finalacno3 = acno3;
        long acnoR = acno2;
        int amtR = amt2;
        String nameR = name2;
        transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money1 = Integer.parseInt(money.getText().toString());
                String monEmpty = money.getText().toString();
                if(money1 > amtMoney){
                    Toast.makeText(Actual_transactions.this, "Amount is Bigger than balance", Toast.LENGTH_SHORT).show();
                } else if (money1 == 0) {
                    Toast.makeText(Actual_transactions.this, "Enter Amount Correctly", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(Actual_transactions.this, PinEnter.class);
                    intent1.putExtra("acno3", finalacno3);
                    intent1.putExtra("amtE", money1);
                    intent1.putExtra("amtA", amtMoney);
                    intent1.putExtra("acnoR", acnoR);
                    intent1.putExtra("amtR", amtR);
                    intent1.putExtra("nameR", nameR);
                    startActivity(intent1);
                }
            }
        });
    }
}