package com.example.qdk.qdk_recept20.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qdk.qdk_recept20.DataBase.MyDatabaseHelper;
import com.example.qdk.qdk_recept20.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/4/4.
 */

public class TallyCastActivity extends Activity{

    private MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"GoodsClass.db",null,1);
    private Spinner spinner;
    private String[] storeName=new String[]{"衣服饰品","食品酒水","居家物业","行车交通","交流通讯","休闲娱乐","学习进修","人情来往","医疗保险"};
    private String commodityClass;
    private Button button;
    private EditText editText_name;
    private EditText editText_money;
    private String time;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chargeup_layout);
        spinner=(Spinner)findViewById(R.id.store_name_spinner);
        button=(Button)findViewById(R.id.charge_ok);
        spinner.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,storeName));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                commodityClass=storeName[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(TallyCastActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("是否保存输入的账单信息");
                dialog.setCancelable(false);
                dialog.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertCommodity();
                        Toast.makeText(TallyCastActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }

    private void insertCommodity(){
        editText_money=(EditText)findViewById(R.id.charge_money);
        editText_name=(EditText)findViewById(R.id.charge_name);
        String name=editText_name.getText().toString();
        String moneyStr=editText_money.getText().toString();
        double money=Double.parseDouble(moneyStr);
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate=new Date(System.currentTimeMillis());
        time=format.format(curDate);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("time",time);
        values.put("id","");
        values.put("name",name);
        values.put("price",money);
        values.put("class",commodityClass);
        db.insert("CommodityClass",null,values);
        values.clear();
        Cursor cursor=db.query("GoodsClass",new String[]{"name,cost,left"},"name like ?",new String[]{commodityClass},null,null,null);
        if (cursor.moveToFirst()){
            do{
                double cost=cursor.getDouble(cursor.getColumnIndex("cost"));
                double left=cursor.getDouble(cursor.getColumnIndex("left"));
                values.put("cost",money+cost);
                values.put("left",left-money);
                db.update("GoodsClass",values,"name =?",new String[]{commodityClass});
            }while (cursor.moveToNext());
        }

    }

    /**
     * Created by lenovo on 2017/3/26.
     */

    public static class DiscountActivity extends Activity {
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            Intent intent=getIntent();
            String num=intent.getStringExtra("extra_num");
            selectView(num);

        }

        private void selectView(String num){
            switch (num){
                case "0":
                    setContentView(R.layout.ls_market_discount_layout);
            }
        }
    }
}
