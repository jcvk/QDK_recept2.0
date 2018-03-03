package com.example.qdk.qdk_recept20.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.qdk.qdk_recept20.Activity.TallyCastActivity;
import com.example.qdk.qdk_recept20.Activity.InvoiceRecordActivity;
import com.example.qdk.qdk_recept20.Activity.GoodsActivity;
import com.example.qdk.qdk_recept20.Activity.NfcActivity;
import com.example.qdk.qdk_recept20.Activity.WantBuyNoteActivity;
import com.example.qdk.qdk_recept20.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/15.
 */

public class Fragment_two extends Fragment {

    private GridView grid;
    private List<Map<String,Object>> data_list;//List有arrayList，有MapList这是MapList
    private SimpleAdapter simpleAdapter;//简单适配器
    private int[] icon={R.drawable.want_buy,R.drawable.personal_info,R.drawable.personal_set,
            R.drawable.trade_detail,R.drawable.tally,R.drawable.more,};
    private String[] iconName={"购买商品","手动记账","交易明细","预购清单","生成发票","查看记录"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.frag_two_layout,container,false);
        grid=(GridView)view.findViewById(R.id.grid_view);//这里不是Activity中所以不能直接用findViewById
        data_list=new ArrayList<>();//分配空间
        for(int i=0;i<icon.length;i++){
            Map<String,Object> map=new HashMap<>();//创建每一个map
            map.put("image",icon[i]);
            map.put("text",iconName[i]);//分别对每一个map赋值
            data_list.add(map);//再将每一个map放入List中
        }

        String[] from={"image","text"};//为image和text设置格式
        int[] to={R.id.image_1,R.id.text_1};

        simpleAdapter=new SimpleAdapter(getActivity(),data_list,R.layout.invoice_item,from,to);
        grid.setAdapter(simpleAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        setChoseItem(0);
                        break;
                    case 1:
                        setChoseItem(1);
                        break;
                    case 3:
                        setChoseItem(3);
                        break;
                    case 2:
                        setChoseItem(2);
                        break;
                    case 4:
                        setChoseItem(4);
                        break;
                    case 5:
                        setChoseItem(5);
                        break;

                }
            }
        });

        return view;
    }

    public void setChoseItem(int index){
        switch (index){
            case 0:
                Intent intent=new Intent(getActivity(),NfcActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1=new Intent(getActivity(),TallyCastActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2=new Intent(getActivity(),InvoiceRecordActivity.class);
                intent2.putExtra("sign_button","2");
                startActivity(intent2);
                break;
            case 3:
                Intent intent3=new Intent(getActivity(), WantBuyNoteActivity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4=new Intent(getActivity(),InvoiceRecordActivity.class);
                intent4.putExtra("sign_button","4");
                startActivity(intent4);
                break;
            case 5:
                Intent intent5=new Intent(getActivity(),GoodsActivity.class);
                String nullDate="";
                intent5.putExtra("extra_data", nullDate);
                Log.d("FragContainActivity", nullDate+"fuck you");
                startActivity(intent5);
                break;

        }


    }

}
