package com.example.funds;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.funds.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ImageView back, history;
    ActivityMain2Binding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        back = findViewById(R.id.back);
        history = findViewById(R.id.history);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Show_History.class);
                startActivity(intent);
            }
        });
        DBHelper DB = new DBHelper(this);
        ArrayList<ContactModel> arrModel = DB.getInfo();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Long> acno = new ArrayList<>();
        ArrayList<Integer> amt = new ArrayList<>();
        ArrayList<Integer> pin = new ArrayList<>();
        for(ContactModel contactModel : arrModel){
            name.add(contactModel.getName());
            acno.add(contactModel.getAcno());
            amt.add(contactModel.getAmt());
            pin.add(contactModel.getPin());
        }
        int index = 0;
        int[] amt1 = new int[name.size()];
        int[] pin1 = new int[pin.size()];
        String[] name1 = new String[name.size()];
        long[] acno1 = new long[acno.size()];
        for(String string : name)
            name1[index++] = string;
        index = 0;
        for(Long l1 : acno)
            acno1[index++] = l1;
        index = 0;
        for(Integer integer : amt)
            amt1[index++] = integer;
        index = 0;
        for(Integer integer : pin)
            pin1[index++] = integer;
        int[] imageList = {R.drawable.ashika, R.drawable.ashok, R.drawable.ajun, R.drawable.charan, R.drawable.geeta, R.drawable.rekha, R.drawable.darshan, R.drawable.daya, R.drawable.sayali, R.drawable.aditya};

        for(int i=0; i<imageList.length; i++){
            listData = new ListData(imageList[i], acno1[i], amt1[i], name1[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(MainActivity2.this, dataArrayList);
        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(MainActivity2.this, user_profile.class);
                intent.putExtra("image", imageList[i]);
                intent.putExtra("acno1", acno1[i]);
                intent.putExtra("amt", amt1[i]);
                intent.putExtra("name", name1[i]);
                startActivity(intent);
            }
        });
    }
}