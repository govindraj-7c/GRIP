package com.example.funds;

public class ListData {
    int image, amount;
    long accountNo;

    String name;

    public ListData(int image, long accountNo, int amount, String name) {
        this.image = image;
        this.accountNo = accountNo;
        this.amount = amount;
        this.name = name;
    }
}
