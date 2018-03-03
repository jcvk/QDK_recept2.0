package com.example.qdk.qdk_recept20.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.DataBase.MyDatabaseHelper;
import com.example.qdk.qdk_recept20.R;

/**
 * Created by lenovo on 2017/3/30.
 */

public class InvoiceShowActivity extends Activity {
    private MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "GoodsClass.db", null, 1);
    private TextView textTime;
    private TextView textName;
    private TextView textEachMuch;
    private TextView textSumMuch;
    private String strName = "";
    private String strEachMuch = "";
    String time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_layout);
        Intent intent = getIntent();
        String commodityStrData = intent.getStringExtra("extra_commodityStr");
        time = commodityStrData.substring(0, 20);
        String commodityStr = commodityStrData.substring(20, commodityStrData.length());
        textTime = (TextView) findViewById(R.id.time_invoice);
        textSumMuch = (TextView) findViewById(R.id.sum_much);
        textTime.setText(time);
        textSumMuch.setText(getPrice(commodityStr) + "元");

    }

    private double getPrice(String commodityStr) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        double priceSum = 0;
        for (int i = 0; i < commodityStr.length(); ) {
            String eachCommodity = commodityStr.substring(i, i + 12);
            cursor = db.query("CommodityClass", new String[]{"id,price,name,time"}, "id like ? and time like ?", new String[]{eachCommodity, time}, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    if (time.equals("")) continue;
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    strName = strName + name + "\n";
                    strEachMuch = strEachMuch + price + "元" + "\n";

                    priceSum = priceSum + price;

                } while (cursor.moveToNext());
            }
            cursor.close();
            i = i + 21;
        }
        textName = (TextView) findViewById(R.id.commodity_invoice);
        textEachMuch = (TextView) findViewById(R.id.each_much);
        textName.setText(strName);
        textEachMuch.setText(strEachMuch);
        return priceSum;

    }
}
