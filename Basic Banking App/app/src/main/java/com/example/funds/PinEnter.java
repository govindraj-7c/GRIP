package com.example.funds;

import static com.google.android.material.internal.ViewUtils.showKeyboard;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PinEnter extends AppCompatActivity {
    ImageView back2;
    CardView tick;
    EditText pin1, pin2, pin3, pin4;
    int counter = 0;
    private int selectPinPosition = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_enter);
        tick = findViewById(R.id.tick);
        pin1 = findViewById(R.id.pin1);
        pin2 = findViewById(R.id.pin2);
        pin3 = findViewById(R.id.pin3);
        pin4 = findViewById(R.id.pin4);
        back2 = findViewById(R.id.back3);
        //setEditTextTextWatcher();
        pin1.addTextChangedListener(new PinTextWatcher(pin1, pin2, null));
        pin2.addTextChangedListener(new PinTextWatcher(pin2, pin3, pin1));
        pin3.addTextChangedListener(new PinTextWatcher(pin3, pin4, pin2));
        pin4.addTextChangedListener(new PinTextWatcher(pin4, null, pin3));

        pin1.setOnKeyListener(new PinKeyListener(pin1, null));
        pin2.setOnKeyListener(new PinKeyListener(pin2, pin1));
        pin3.setOnKeyListener(new PinKeyListener(pin3, pin2));
        pin4.setOnKeyListener(new PinKeyListener(pin4, pin3));
        
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinEnter.this, Actual_transactions.class);
                startActivity(intent);
            }
        });
        Intent intent = this.getIntent();
        long acno3 = 741369852654L;
        int amtE = 500;
        int amtA = 10000;
        long acnoR2 = 741369852654L;
        int amtR2 = 5000;
        String nameR = null;
        if(intent != null){
            acno3 = intent.getLongExtra("acno3", 741369852654L);
            amtE = intent.getIntExtra("amtE", 500);
            amtA = intent.getIntExtra("amtA", 10000);
            acnoR2 = intent.getLongExtra("acnoR", 741369852654L);
            amtR2 = intent.getIntExtra("amtR", 5000);
            nameR = intent.getStringExtra("nameR");
        }
        String acnoRU = String.valueOf(acnoR2);
        int amtRU = amtR2;
        String acnoU = String.valueOf(acno3);
        DBHelper dbHelper = new DBHelper(this);
        String pinU = dbHelper.getPin(acnoU);
        int amtE1 = amtE;
        int amtA1 = amtA;
        String nameR2 = nameR;
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total = pin1.getText().toString()+pin2.getText().toString()+pin3.getText().toString()+pin4.getText().toString();
                if(total.equals(pinU)){
                    dbHelper.amountReduce(amtA1,amtE1,acnoU);
                    dbHelper.amountIncreased(amtRU,amtE1,acnoRU);
                    String transactionID = generateRandom();
                    dbHelper.insertHistory(transactionID,amtE1,nameR2);
                    popUp();
                } else {
                    Toast.makeText(PinEnter.this, "Wrong Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void popUp(){
        final Dialog dialog = new Dialog(PinEnter.this);
        dialog.setContentView(R.layout.custompopup);
        TextView wait = dialog.findViewById(R.id.wait);
        ImageView tickS = dialog.findViewById(R.id.tickS);
        TextView success = dialog.findViewById(R.id.success);
        TextView ok = dialog.findViewById(R.id.ok);
        ProgressBar pb = dialog.findViewById(R.id.progressBar);

        final CountDownTimer countDownTimer = new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                wait.setVisibility(View.INVISIBLE);
                pb.setVisibility(View.INVISIBLE);
                tickS.setVisibility(View.VISIBLE);
                success.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PinEnter.this, MainActivity2.class);
                        startActivity(intent);
                    }
                });
            }
        };
        dialog.show();
        countDownTimer.start();
    }
    public String generateRandom(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            boolean isChar = random.nextBoolean(); // Determine if the next character should be a letter or a digit

            if (isChar) {
                // Generate a random uppercase letter ('A' to 'Z')
                char randomChar = (char) (random.nextInt(26) + 'a');
                stringBuilder.append(randomChar);
            } else {
                // Generate a random digit (0 to 9)
                int randomDigit = random.nextInt(10);
                stringBuilder.append(randomDigit);
            }
        }

        return stringBuilder.toString();
    }
}