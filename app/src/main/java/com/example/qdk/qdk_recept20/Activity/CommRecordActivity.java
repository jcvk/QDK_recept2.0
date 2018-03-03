package com.example.qdk.qdk_recept20.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.Class.CommodityClass;
import com.example.qdk.qdk_recept20.DataBase.MyDatabaseHelper;
import com.example.qdk.qdk_recept20.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/2/10.
 */

public class CommRecordActivity extends Activity {

    private MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"GoodsClass.db",null,1);
    private List<CommodityClass> commodityClassList =new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cost_layout);
        Intent intent=getIntent();
        String goodsName=intent.getStringExtra("extra_GoodsName");
        showCommodity(goodsName);
        CommodityAdapter commodityAdapter=new CommodityAdapter(CommRecordActivity.this,R.layout.commodity_item, commodityClassList);
        ListView listView=(ListView)findViewById(R.id.commodity_list_view);
        listView.setAdapter(commodityAdapter);


    }

//    private void showCommodity(String commodityStrData){
//        SQLiteDatabase db=dbHelper.getWritableDatabase();
//        Cursor cursor;
//        for(int i=0;i<commodityStrData.length();){
//            String eachCommodityID=commodityStrData.substring(i,i+12);
//            String eachCommodityTime=commodityStrData.substring(i+12,i+32);
//            cursor=db.query("CommodityClass",new String[]{"time,id,name,class,price"},"id=? and time=?",new String[]{eachCommodityID,eachCommodityTime},null,null,null);
//            if(cursor.moveToFirst()){
//                do{
//                    String name=cursor.getString(cursor.getColumnIndex("name"));
//                    double price=cursor.getDouble(cursor.getColumnIndex("price"));
//                    String time=cursor.getString(cursor.getColumnIndex("time"));
//                    CommodityClass commodity=new CommodityClass(time,name,price);
//                    commodityClassList.add(commodity);
//                }while (cursor.moveToNext());
//            }
//            cursor.close();
//            i=i+32;
//        }
//
//    }

    private void showCommodity(String goodsName){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor;
        cursor=db.query("CommodityClass",new String[]{"time,name,class,price"},"class=?",new String[]{goodsName},null,null,null);
        if(cursor.moveToFirst()){
            do{
                String time=cursor.getString(cursor.getColumnIndex("time"));
                if(time.equals("")) continue;
                String name=cursor.getString(cursor.getColumnIndex("name"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                CommodityClass commodityClass =new CommodityClass(time,name,price);
                commodityClassList.add(commodityClass);
            }while (cursor.moveToNext());
            cursor.close();
        }

    }

    public class CommodityAdapter extends ArrayAdapter<CommodityClass>{
        private int resourceID;

        public CommodityAdapter(Context context,int textViewResourceId,List<CommodityClass> objects){
            super(context,textViewResourceId,objects);
            resourceID=textViewResourceId;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            CommodityClass commodityClass =getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            TextView commodityTime=(TextView)view.findViewById(R.id.commodity_time);
            TextView commodityName=(TextView)view.findViewById(R.id.commodity_name);
            TextView commodityPrice=(TextView)view.findViewById(R.id.commodity_price);
            commodityTime.setText(commodityClass.getCommodityTime());
            commodityName.setText(commodityClass.getCommodityName());
            commodityPrice.setText(commodityClass.getCommodityPrice()+"元");
            return view;
        }

    }
}
