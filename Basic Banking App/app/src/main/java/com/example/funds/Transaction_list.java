package com.example.funds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.funds.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class Transaction_list extends AppCompatActivity {
    ImageView back;
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transaction_list.this, user_profile.class);
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

        Intent intent = this.getIntent();
        int amt3 = 10000;
        long acno3 = 741369852654L;
        String name3 = null;
        if(intent != null){
            name3 = intent.getStringExtra("name");
            acno3 = intent.getLongExtra("acno", 741369852654L);
            amt3 = intent.getIntExtra("amt", 10000);
        }
        String finalname3 = name3;
        int finalamt3 = amt3;
        long finalacno3 = acno3;
        for(int i=0; i<imageList.length; i++){
            listData = new ListData(imageList[i], acno1[i], amt1[i], name1[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(Transaction_list.this, dataArrayList);
        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(Transaction_list.this, Actual_transactions.class);
                intent.putExtra("image", imageList[i]);
                intent.putExtra("acno1", acno1[i]);
                intent.putExtra("name", name1[i]);
                intent.putExtra("amt1", amt1[i]);
                intent.putExtra("name3", finalname3);
                intent.putExtra("amt3", finalamt3);
                intent.putExtra("acno3", finalacno3);
                startActivity(intent);
            }
        });
//        Intent intent1 = new Intent(Transaction_list.this, Actual_transactions.class);
//        intent1.putExtra("name3", finalname3);
//        intent1.putExtra("acno3", finalacno3);
//        intent1.putExtra("amt3", finalamt3);
//        startActivity(intent1);
    }
}