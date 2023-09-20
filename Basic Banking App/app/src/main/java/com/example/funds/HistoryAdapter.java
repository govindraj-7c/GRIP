package com.example.funds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<HistoryInfo> {
    public HistoryAdapter(@NonNull Context context, ArrayList<HistoryInfo> historyArrayList) {
        super(context, R.layout.history_list, historyArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        HistoryInfo historyInfo = getItem(position);
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.history_list, parent, false);
        }
        TextView receiverName = view.findViewById(R.id.receiverName);
        TextView actualid = view.findViewById(R.id.actualID);
        TextView receivedAmt = view.findViewById(R.id.doller);
        receiverName.setText(historyInfo.nameR);
        actualid.setText(historyInfo.id);
        receivedAmt.setText(Integer.toString(historyInfo.amtR));
        return view;
    }
}
