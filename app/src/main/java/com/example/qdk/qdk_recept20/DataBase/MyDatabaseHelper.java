package com.example.qdk.qdk_recept20.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/3/7.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_GOODS = "create table GoodsClass("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "budget real, "
            + "cost real, "
            + "image integer, "
//            +"commodity text, "
            + "left real)";


    /**
     * 这里会出现一个bug，是因为时间最开始定义为了主属性，但是同一时间得到的商品主属性会冲突，所以这里直接定义
     * 一个无关的递增编号作为主属性
     */
    public static final String CREATE_COMMODITY = "create table CommodityClass("
            + "num integer primary key autoincrement, "
            + "time text, "
            + "id text, "
            + "name text, "
            + "price real, "
            + "class text)";

    public static final String CREATE_MARKET = "create table Market("
            + "time text primary key, "
            + "id text text, "
            + "commodity text, "
            + "place text)";

    public static final String CREATE_EDIT = "create table Edit("
            + "id integer primary key autoincrement, "
            + "content text, "
            + "time text)";


    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GOODS);
        db.execSQL(CREATE_COMMODITY);
        db.execSQL(CREATE_MARKET);
        db.execSQL(CREATE_EDIT);
        Toast.makeText(mContext, "create succeeded", Toast.LENGTH_LONG).show();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
