package com.example.qdk.qdk_recept20.DataBase;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.Class.GoodsClass;
import com.example.qdk.qdk_recept20.R;

import java.util.List;

/**
 * Created by lenovo on 2017/2/9.
 */

public class GoodsAdapter extends ArrayAdapter<GoodsClass>{
    private int resourecId;

    public GoodsAdapter(Context context, int textViewResourceId, List<GoodsClass> objects){
        super(context,textViewResourceId,objects);
        resourecId=textViewResourceId;//resourecId是子布局
    }

    public View getView(int position,View convertView,ViewGroup parent){
        GoodsClass goodsClass =getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourecId,null);
            viewHolder=new ViewHolder();
            viewHolder.image=(ImageView)view.findViewById(R.id.goods_image);
            viewHolder.goodname=(TextView)view.findViewById(R.id.good_name);
            viewHolder.budget=(TextView)view.findViewById(R.id.good_budget);//得到两个两个文本布局
            view.setTag(viewHolder);//将这两个文本布局放到子布局中
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.image.setImageResource(goodsClass.getImageId());
        viewHolder.goodname.setText(goodsClass.getGoods_name());
        viewHolder.budget.setText("预算"+ goodsClass.getBudget()+"元"+" "+"已用"+ goodsClass.getCost()+"元"+" "+"剩余"+ goodsClass.getLeft()+"元");
        return view;

    }

    class ViewHolder{
        TextView goodname;
        TextView budget;
        ImageView image;

    }

}
