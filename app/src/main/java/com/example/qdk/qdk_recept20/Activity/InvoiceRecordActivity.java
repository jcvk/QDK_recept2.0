package com.example.qdk.qdk_recept20.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.Class.MarketClass;
import com.example.qdk.qdk_recept20.DataBase.MyDatabaseHelper;
import com.example.qdk.qdk_recept20.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/29.
 */

public class InvoiceRecordActivity extends Activity {

    private MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"GoodsClass.db",null,1);
    private List<MarketClass> MarketList=new ArrayList<>();
    String time;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createinvoice_layout);
        Intent intent=getIntent();
        String sign_button=intent.getStringExtra("sign_button");
        initMarket();
        MarketAdapter marketAdapter=new MarketAdapter(InvoiceRecordActivity.this,R.layout.market_item
        ,MarketList);
        ListView listView=(ListView)findViewById(R.id.market_list_view);
        listView.setAdapter(marketAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MarketClass marketRecord=MarketList.get(position);
//                Intent intent=new Intent();
//                intent.setClass(InvoiceRecordActivity.this,InvoiceShowActivity.class);
//                intent.putExtra("extra_commodityStr",marketRecord.getRecordTime()+marketRecord.getRecordCommodity());
//                startActivity(intent);
//            }
//        });
        if(sign_button.equals("4")){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MarketClass marketClass =MarketList.get(position);
                    Intent intent=new Intent();
                    intent.setClass(InvoiceRecordActivity.this,InvoiceShowActivity.class);
                    intent.putExtra("extra_commodityStr", marketClass.getRecordTime()+ marketClass.getRecordCommodity());
                    startActivity(intent);
                }
            });
        }
        if(sign_button.equals("2")){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MarketClass marketClass =MarketList.get(position);
                    Intent intent=new Intent();
                    intent.setClass(InvoiceRecordActivity.this,LittleInvoiceShowActivity.class);
                    intent.putExtra("extra_commodityStr", marketClass.getRecordTime()+ marketClass.getRecordCommodity());
                    startActivity(intent);
                }
            });
        }



    }

    public void initMarket(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Market",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Log.d("InvoiceRecordActivity","into initMarket");
//                +"id text primary key, "
//                        +"time text, "
//                        +"commodity text, "
//                        +"place text)";
                String id=cursor.getString(cursor.getColumnIndex("id"));
                time=cursor.getString(cursor.getColumnIndex("time"));
                String commodity=cursor.getString(cursor.getColumnIndex("commodity"));
                String place=cursor.getString(cursor.getColumnIndex("place"));
                MarketClass marketClass =new MarketClass(id,place,time,commodity);
                MarketList.add(marketClass);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }

    public class MarketAdapter extends ArrayAdapter<MarketClass>{
        private int resourceID;

        public MarketAdapter(Context context,int textViewResourceId,List<MarketClass> objects){
            super(context,textViewResourceId,objects);
            resourceID=textViewResourceId;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            MarketClass marketClass =getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            TextView recordTime=(TextView)view.findViewById(R.id.market_item_time);
            TextView recordPlace=(TextView)view.findViewById(R.id.market_item_name);
            TextView recordPrice=(TextView)view.findViewById(R.id.market_item_commodity);
            recordTime.setText(marketClass.getRecordTime());
            recordPlace.setText(marketClass.getMarketPlace());
            recordPrice.setText("共计"+getPrice(marketClass.getRecordCommodity())+"元");
            return view;

        }

        private double getPrice(String commodityStr){
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            Cursor cursor;
            double priceSum=0;
            for(int i=0;i<commodityStr.length();){
                String eachCommodity=commodityStr.substring(i,i+12);
                Log.d("InvoiceRecordActivity",eachCommodity+"!!@@@@!!!!!");
                cursor=db.query("CommodityClass",new String[]{"id,price,time"},"id like ? and time like ?",new String[]{eachCommodity,time},null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("InvoiceRecordActivity",price+"!!@@@@!!!!!");
                        priceSum=priceSum+price;

                    }while (cursor.moveToNext());
                }
                cursor.close();
                i=i+21;
            }
            return priceSum;

        }
    }
}
