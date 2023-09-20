package com.example.funds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public DBHelper(Context context) {
        super(context, "bank.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ACCOUNT(name TEXT, acno INT PRIMARY KEY, amt INT, pin INT)");
        db.execSQL("INSERT INTO ACCOUNT(name,acno,amt,pin) VALUES('Ashika Patel', 741369852654, 10000, 4561), ('Ashok Reddy', 987654123412, 8000, 3245)" +
                ",('Arjun Bhatt', 456321789357, 5000, 7845),('Charan Verma', 134659871159, 7000, 7891),('Geeta Chawla', 654789123183, 10000, 6234)" +
                ",('Rekha Chavan', 378546541942, 4500, 1234),('Darshan Joshi', 845210397024, 5000, 7124),('Daya Rathod', 512347850751, 7000, 4452)" +
                ",('Sayali Sharma', 357951486032, 12000, 3651),('Aditya Singh', 223694510100, 8500, 7745)");
        db.execSQL("CREATE TABLE HISTORY(name TEXT, amt INT, id TEXT)");
        Log.d("table", "Created Successfully History");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ACCOUNT");
        onCreate(db);
    }
   public ArrayList<ContactModel> getInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ACCOUNT", null);
        ArrayList<ContactModel> arrModel = new ArrayList<>();
        while(cursor.moveToNext()){
            ContactModel model = new ContactModel();
            model.name = cursor.getString(0);
            model.acno = cursor.getLong(1);
            model.amt = cursor.getInt(2);
            model.pin = cursor.getInt(3);
            arrModel.add(model);
        }
        return arrModel;
   }
   public String getPin(String accountNo1){
        String pinA = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT PIN FROM ACCOUNT WHERE acno = ?", new String[]{accountNo1});
        while(cursor.moveToNext()){
            pinA = cursor.getString(0);
        }
        return pinA;
   }
   public void amountReduce(int ActaulAmt, int EnterAmt, String firstAcno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int reduceAmt = ActaulAmt - EnterAmt;
        contentValues.put("amt", reduceAmt);
        int amtR = db.update("account", contentValues, "acno = " +firstAcno, null);
   }
    public void amountIncreased(int RAmt, int EnterAmt, String RAcno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int reduceAmt = RAmt + EnterAmt;
        contentValues.put("amt", reduceAmt);
        int amtR = db.update("account", contentValues, "acno = " +RAcno, null);
    }
    public void insertHistory(String trans, int receivedAmt, String rName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", rName);
        contentValues.put("amt", receivedAmt);
        contentValues.put("id", trans);
        db.insert("HISTORY", null, contentValues);
    }
    public ArrayList<HistoryData> getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HISTORY", null);
        ArrayList<HistoryData> arrModel = new ArrayList<>();
        while(cursor.moveToNext()){
            HistoryData historyData = new HistoryData();
            historyData.receiverName = cursor.getString(0);
            historyData.receivedAmt = cursor.getInt(1);
            historyData.transactionID = cursor.getString(2);
            arrModel.add(historyData);
        }
        return arrModel;
    }
    void deleteAllRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("HISTORY", null, null);
    }
}
