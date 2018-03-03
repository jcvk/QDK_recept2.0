package com.example.qdk.qdk_recept20.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qdk.qdk_recept20.Class.GoodsClass;
import com.example.qdk.qdk_recept20.DataBase.GoodsAdapter;
import com.example.qdk.qdk_recept20.DataBase.MyDatabaseHelper;
import com.example.qdk.qdk_recept20.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2017/2/10.
 */

public class GoodsActivity extends Activity{

    private MyDatabaseHelper dbHelper;
    private List<GoodsClass> goodsClassList =new ArrayList<GoodsClass>();
    private String time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_layout);
        Intent intent = getIntent();
        String goodDateStore = intent.getStringExtra("extra_data");
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate=new Date(System.currentTimeMillis());
        time=format.format(curDate);

//        String goodStore=goodDateStore.substring(0,4);
////        Cursor cursor=queryMarket(goodStore);
//
//        String goodDate=goodDateStore.substring(4,goodDateStore.length());

        //classMarket(goodStore,goodDate);

        createDBMS();
        addDBMS();
        //classGood(goodDate);//这个classStr是商品分类
        if(!goodDateStore.equals("")){

            String goodStore=goodDateStore.substring(0,3);
//        Cursor cursor=queryMarket(goodStore);

            String goodDate=goodDateStore.substring(3,goodDateStore.length());
//            LogMainActivity.d("FragContainActivity","into this fuck");
//            classGood(goodDate);
            for(int i=0;i<goodDate.length();){
                String eachGoodDate=goodDate.substring(i,i+21);
                classGood(eachGoodDate);
                i=i+21;
            }
            classMarket(goodStore,goodDate);
        }

        initGoods();
        GoodsAdapter adapter = new GoodsAdapter(GoodsActivity.this, R.layout.goods_item, goodsClassList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsClass goodsClass = goodsClassList.get(position);
                Intent intent = new Intent();
                intent.setClass(GoodsActivity.this,CommRecordActivity.class);
                intent.putExtra("extra_GoodsName", goodsClass.getGoods_name());
                startActivity(intent);

            }
        });
    }

    /*
    为什么在classMarket中用这种添加方式，而在商品分类的时候又用另一种先初始化的方式，是因为商品分类的时候，商品和商品分类之间的关系
    很多，如果要写下来，每一件商品都对应一个分类，那样会用很多的switch语句。
     */
    private void classMarket(String goodStore,String goodDate){
//        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//        Date curDate=new Date(System.currentTimeMillis());
//        time=format.format(curDate);
        switch (goodStore){
            case "001":
                //id,time,commodity,place
                insertMarketData(goodStore,time,goodDate,"利生超市");
                break;
            case "002":
                insertMarketData(goodStore,time,goodDate,"学佳超市");
                break;
            case "003":
                insertMarketData(goodStore,time,goodDate,"东大商店");
                break;
            case "004":
                insertMarketData(goodStore,time,goodDate,"华苑超市");
        }

    }


    public void classGood(String goodDate){
        String goodsNumberStr=goodDate.substring(0,12);
        String goodsPriceStr=goodDate.substring(12,21);
        double goodsPriceDou=priceStrChangeInt(goodsPriceStr);
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
//        Date curDate=new Date(System.currentTimeMillis());
//        String time=format.format(curDate);
//        ContentValues values=new ContentValues();
//        if(cursor.moveToFirst()){
//            do{
//                String commodity=cursor.getString(cursor.getColumnIndex("commodity"));
//                values.put("commodity",commodity+goodsNumberStr);
//                values.put("time",time);
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
        queryCommodity(goodsNumberStr,goodsPriceDou);

    }

//    private Cursor queryMarket(String idStr){
////        SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
////        Date curDate=new Date(System.currentTimeMillis());
////        String time=format.format(curDate);
//        SQLiteDatabase db=dbHelper.getWritableDatabase();
//        Cursor cursor=db.query("Market",new String[]{"id,place,commodity,time"},"id like ?",new String[]{idStr},null,null,null);
//        return cursor;
//
//
//    }


    double priceStrChangeInt(String goodsPriceStr){
        double price=Double.parseDouble(goodsPriceStr);
        return price;

    }


    private void createDBMS(){
        dbHelper=new MyDatabaseHelper(this,"GoodsClass.db",null,1);
        dbHelper.getWritableDatabase();
    }

    private void addDBMS(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int num;
        Cursor cursor=db.rawQuery("select*from GoodsClass",null);
        num=cursor.getCount();
        if(num==0){
//            db.execSQL("insert into GoodsClass (name,budget,cost,left) values(?, null, ?, ?)",
//                    new String[]{"衣服饰品","1000","1000"});
//            db.execSQL("insert into GoodsClass (name,budget,cost,left) values(?, null, ?, ?)",
//                    new String[]{"食品酒水","500","500"});
//            db.execSQL("insert into CommodityClass (id,name,price,class) values(?, ?, null, ?)",
//                    new String[]{"G23020200182","矿泉水","食品酒水"});
//            db.execSQL("insert into CommodityClass (id,name,price,class) values(?, ?, null, ?)",
//                    new String[]{"G17010100102","薯片","食品酒水"});
//            LogMainActivity.d("FragContainActivity","add date ok");
            insertGoodsData("衣服饰品",1000,0,1000,R.drawable.clothes);
            insertGoodsData("食品酒水",2500,0,2500,R.drawable.foodanddrink);
            insertGoodsData("居家物业",3000,0,3000,R.drawable.home);
            insertGoodsData("行车交通",300,0,300,R.drawable.transport);
            insertGoodsData("交流通讯",200,0,200,R.drawable.community);
            insertGoodsData("休闲娱乐",500,0,500,R.drawable.game);
            insertGoodsData("学习进修",1000,0,1000,R.drawable.study);
            insertGoodsData("人情来往",1000,0,1000,R.drawable.relation);
            insertGoodsData("医疗保险",350,0,350,R.drawable.hospital);
            insertCommodityData("","G17010100102","薯片",0.00,"食品酒水");
            insertCommodityData("","G23020200182","矿泉水",0.00,"食品酒水");
            insertCommodityData("","R31070300247","牙刷",0.00,"食品酒水");
            insertCommodityData("","R40060100465","电磁炉",0.00,"食品酒水");
            insertCommodityData("","R40240200499","电视机",0.00,"食品酒水");
            insertCommodityData("","R40240300500","自行车",0.00,"食品酒水");
//            insertStoreData("利生超市","0001");
//            insertStoreData("学佳超市","0002");
//            insertStoreData("东大商店","0003");
//            insertStoreData("华苑超市","0004");
        }
    }

    private void queryCommodity(String goodsNumberStr,double goodsPriceDou){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("CommodityClass",new String[]{"time,id,name,class,price"},"id=?",new String[]{goodsNumberStr},null,null,null);
        if(cursor.moveToFirst()){
            do{
                ////找到commodity，并修改价格
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String classStr=cursor.getString(cursor.getColumnIndex("class"));
                //double price=cursor.getDouble(cursor.getColumnIndex("price"));
                //LogMainActivity.d("FragContainActivity",price+"");
                ContentValues values=new ContentValues();
                Log.d("GoodsActivity",goodsNumberStr+name+goodsPriceDou+classStr);
                insertCommodityData(time,goodsNumberStr,name,goodsPriceDou,classStr);
                //values.put("price",goodsPriceDou);
                //db.update("CommodityClass",values,"id = ?",new String[]{goodsNumberStr});

                //price=cursor.getDouble(cursor.getColumnIndex("price"));
//                if (cursor.moveToFirst()){//查找数据不能少了这个if语句
//                    do {
//                        price=cursor.getDouble(cursor.getColumnIndex("price"));
//                    }while (cursor.moveToNext());
//                }
//                LogMainActivity.d("FragContainActivity",price+"");
                ////找到goods，并修改cost and left数据，和commodity
                //values.clear();
                cursor=db.query("GoodsClass",new String[]{"name,cost,left"},"name like ?",new String[]{classStr},null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        double cost=cursor.getDouble(cursor.getColumnIndex("cost"));
                        double left=cursor.getDouble(cursor.getColumnIndex("left"));
                        //String commodity=cursor.getString(cursor.getColumnIndex("commodity"));
                        values.put("cost",goodsPriceDou+cost);
                        values.put("left",left-goodsPriceDou);
                        //values.put("commodity",commodity+goodsNumberStr+time);//commodity=goodsNumberStr
                        db.update("GoodsClass",values,"name =?",new String[]{classStr});
                    }while(cursor.moveToNext());
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

//    insertStoreData("华苑超市",goodStore,time,goodDate);

    public void insertGoodsData(String nameStr,double budgetDouble,double costDouble,double leftDouble,int imageId){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",nameStr);
        values.put("budget",budgetDouble);
        values.put("cost",costDouble);
        values.put("left",leftDouble);
        values.put("image",imageId);
//        values.put("commodity","");
        db.insert("GoodsClass",null,values);
        values.clear();
    }


    public void insertCommodityData(String timeStr,String idStr,String nameStr,double priceDouble,String classStr){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("time",timeStr);
        values.put("id",idStr);
        values.put("name",nameStr);
        values.put("price",priceDouble);
        values.put("class",classStr);
        db.insert("CommodityClass",null,values);
        values.clear();
    }

//    id,time,commodity,place
    public void insertMarketData(String idStr,String time,String commodity,String place){
        Log.d("FragContainActivity",place+idStr+time+commodity);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Log.d("FragContainActivity",db+"");
        ContentValues values=new ContentValues();
        values.put("id",idStr);
        values.put("time",time);
        values.put("commodity",commodity);
        values.put("place",place);
        db.insert("Market",null,values);
        values.clear();
    }

    public void initGoods(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("GoodsClass",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                double budget=cursor.getDouble(cursor.getColumnIndex("budget"));
                double cost=cursor.getDouble(cursor.getColumnIndex("cost"));
                double left=cursor.getDouble(cursor.getColumnIndex("left"));
                int image=cursor.getInt(cursor.getColumnIndex("image"));
                //String commodity=cursor.getString(cursor.getColumnIndex("commodity"));
                GoodsClass goodsClass =new GoodsClass(name,budget,left,cost,image);
                goodsClassList.add(goodsClass);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

//    public void onPause(){
//        super.onPause();
//        Intent intent=new Intent(GoodsActivity.this,FragContainActivity.class);
//        startActivity(intent);
//    }
//
//    public void onStop(){
//        super.onStop();
////        Intent intent=new Intent(GoodsActivity.this,FragContainActivity.class);
////        startActivity(intent);
//        LogMainActivity.d("FragContainActivity","into stop");
//    }
//
//    public void onDestroy(){
//        super.onDestroy();
//        LogMainActivity.d("FragContainActivity","into destroy");
//    }



}
