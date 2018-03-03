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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.Class.RecordClass;
import com.example.qdk.qdk_recept20.DataBase.MyDatabaseHelper;
import com.example.qdk.qdk_recept20.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/4/4.
 */

public class WantBuyNoteActivity extends Activity {

    private MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"GoodsClass.db",null,1);
    private Button addNote;
    private TextView tv_content;
    private TextView tv_time;
    private List<RecordClass> recordClasses =new ArrayList<>();

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wantbuy_layout);
        addNote=(Button)findViewById(R.id.wb_add_note);
        showEditList();//遍历数据库，把所有的记录放入链表中
        EditListAdapter editListAdapter=new EditListAdapter(WantBuyNoteActivity.this,R.layout.wantbuy_item, recordClasses);
        ListView listView=(ListView)findViewById(R.id.wb_list);
        listView.setAdapter(editListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecordClass recordClass = recordClasses.get(position);
                Intent intent=new Intent();
                intent.setClass(WantBuyNoteActivity.this,EditWantBuyActivity.class);
                intent.putExtra("extra_info",String.valueOf(recordClass.getId()));
                startActivity(intent);
            }
        });
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WantBuyNoteActivity.this,EditWantBuyActivity.class);
                intent.putExtra("extra_info","");
                startActivity(intent);
            }
        });

    }

    private void showEditList(){/////////////待续未完
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor;
        String content;
        cursor=db.query("Edit",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String time=cursor.getString(cursor.getColumnIndex("time"));
                String contentStr=cursor.getString(cursor.getColumnIndex("content"));
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                if(contentStr.length()<=5){
                    content=contentStr;
                }else {
                    content=contentStr.substring(0,5);
                }
                RecordClass recordClass =new RecordClass(id,content,time);
                recordClasses.add(recordClass);
            }while (cursor.moveToNext());
            cursor.close();
        }
    }

    public class EditListAdapter extends ArrayAdapter<RecordClass>{

        private int resourceID;

        public EditListAdapter(Context context, int textViewResourceId, List<RecordClass> objects){
            super(context,textViewResourceId,objects);
            resourceID=textViewResourceId;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            RecordClass recordClass =getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            tv_content=(TextView)view.findViewById(R.id.wb_title);
            tv_time=(TextView)view.findViewById(R.id.wb_time);
            tv_content.setText(recordClass.getContent());
            tv_time.setText(recordClass.getTime());
            return view;
        }
    }

    protected void onRestart(){
        super.onRestart();
        if(recordClasses.size()>0){
            recordClasses.removeAll(recordClasses);
        }
        showEditList();
        EditListAdapter editListAdapter=new EditListAdapter(WantBuyNoteActivity.this,R.layout.wantbuy_item, recordClasses);
        ListView listView=(ListView)findViewById(R.id.wb_list);
        listView.setAdapter(editListAdapter);
    }
}
