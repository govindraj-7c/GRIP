package com.example.funds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.funds.databinding.ActivityMain2Binding;
import com.example.funds.databinding.ActivityShowHistoryBinding;

import java.util.ArrayList;

public class Show_History extends AppCompatActivity {
    ImageView back3, delete;
    AlertDialog.Builder builder;
    ActivityShowHistoryBinding binding;
    HistoryAdapter historyAdapter;
    ArrayList<HistoryInfo> historyInfoArrayList = new ArrayList<>();
    HistoryInfo historyInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowHistoryBinding.inflate(getLayoutInflater());
        DBHelper dbHelper = new DBHelper(this);
        setContentView(binding.getRoot());
        delete = findViewById(R.id.delete);
        back3 = findViewById(R.id.back4);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_History.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        builder = new AlertDialog.Builder(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Alert!!")
                        .setMessage("Do you Want to delete all Records?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.deleteAllRecords();
                                Intent intent = new Intent(Show_History.this, MainActivity2.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();;
                            }
                        })
                        .show();
            }
        });
        ArrayList<HistoryData> historyArray = dbHelper.getData();
        ArrayList<String> nameR = new ArrayList<>();
        ArrayList<String> idT = new ArrayList<>();
        ArrayList<Integer> amtR = new ArrayList<>();
        for(HistoryData historyData : historyArray){
            nameR.add(historyData.getReceiverName());
            idT.add(historyData.getTransactionID());
            amtR.add(historyData.getReceivedAmt());
        }
        int index = 0;
        String[] nameR1 = new String[nameR.size()];
        String[] idT1 = new String[idT.size()];
        int[] amtR1 = new int[amtR.size()];
        for(String string : nameR)
            nameR1[index++] = string;
        index = 0;
        for(String string : idT)
            idT1[index++] = string;
        index = 0;
        for(Integer integer : amtR)
            amtR1[index++] = integer;

        for(int i=0; i<amtR1.length; i++){
            historyInfo = new HistoryInfo(idT1[i],nameR1[i],amtR1[i]);
            historyInfoArrayList.add(historyInfo);
        }
        historyAdapter = new HistoryAdapter(Show_History.this, historyInfoArrayList);
        binding.listView.setAdapter(historyAdapter);
        binding.listView.setClickable(false);
    }
}