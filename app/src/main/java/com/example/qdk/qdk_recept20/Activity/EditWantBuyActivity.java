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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qdk.qdk_recept20.DataBase.MyDatabaseHelper;
import com.example.qdk.qdk_recept20.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/4/4.
 */

public class EditWantBuyActivity extends Activity {

    private MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"GoodsClass.db",null,1);
    private TextView tv_time;
    private EditText et_content;
    private Button btn_Save;
    private Button btn_concel;
    String id;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editwantbuy_layout);
        tv_time=(TextView)findViewById(R.id.edit_wb_time);
        et_content=(EditText)findViewById(R.id.edit_wb_edit);
        btn_concel=(Button)findViewById(R.id.btn_cancel);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dataString=sdf.format(date);
        tv_time.setText(dataString);
        Intent intent=getIntent();
        String info=intent.getStringExtra("extra_info");
        if(info.equals("")){
            et_content.setText("");
            id="";
        }else{
            id=info;
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            Cursor cursor=db.query("Edit",new String[]{"id,content"},"id=?",new String[]{id},null,null,null);
            if(cursor.moveToFirst()){
                do{
                    String content=cursor.getString(cursor.getColumnIndex("content"));
                    et_content.setText(content);
                    et_content.requestFocus();
                }while (cursor.moveToNext());
            }
        }
        btn_Save=(Button)findViewById(R.id.btn_save);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=et_content.getText().toString();
                Date date=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String dataNum=sdf.format(date);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
//                values.put("content",content);
//                values.put("time",dataNum);
//                db.insert("Edit",null,values);
//                values.clear();
                if(id.equals("")){
                    values.put("content",content);
                    values.put("time",dataNum);
                    db.insert("Edit",null,values);
                    values.clear();
                }else{
                    Cursor cursor=db.query("Edit",new String[]{"id,content"},"id=?",new String[]{id},null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            values.put("content",content);
                            db.update("Edit",values,"id=?",new String[]{id});
                        }while (cursor.moveToNext());
                    }

                }
                Toast.makeText(EditWantBuyActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });

        btn_concel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(EditWantBuyActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("是否取消保存内容");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }
}
