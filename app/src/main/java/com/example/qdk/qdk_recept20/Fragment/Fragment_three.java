package com.example.qdk.qdk_recept20.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.Activity.MarketShowActivity;
import com.example.qdk.qdk_recept20.R;
import com.example.qdk.qdk_recept20.Class.StoreClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/15.
 */

public class Fragment_three extends Fragment {

    private Context context;


    private List<StoreClass> storeClassList =new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.frag_three_layout,container,false);
        initStoreList(view);

        return view;
    }

    private void initStoreList(View view){
        StoreClass li_sheng=new StoreClass("利生超市","卫龙辣条0.35元一包",R.drawable.dongda);
        StoreClass xue_jia=new StoreClass("学佳超市","卫龙辣条0.35元一包",R.drawable.dongdagouwu);
        StoreClass dong_da=new StoreClass("东大商店","卫龙辣条0.35元一包",R.drawable.huayuan);
        StoreClass hua_yuan=new StoreClass("华苑超市","卫龙辣条0.35元一包",R.drawable.dongda);
        storeClassList.add(li_sheng);
        storeClassList.add(xue_jia);
        storeClassList.add(dong_da);
        storeClassList.add(hua_yuan);

        Store_mumAdapter adapter=new Store_mumAdapter(getActivity(),R.layout.store_mum_item, storeClassList);

        ListView listView=(ListView)view.findViewById(R.id.member_list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StoreClass storeClass = storeClassList.get(position);
                if(storeClass.getName()=="利生超市"){

                    Log.d("FragContainActivity",getActivity()+"fuck you");
                    Intent intent=new Intent(getActivity(),MarketShowActivity.class);
                    startActivity(intent);
                }
            }
        });



    }

    public class Store_mumAdapter extends ArrayAdapter<StoreClass>{
        private int resourceID;

        public Store_mumAdapter(Context context,int textViewResourceID, List<StoreClass> objects){
            super(context,textViewResourceID,objects);
            resourceID=textViewResourceID;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            StoreClass storeClass =getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            TextView store_mum=(TextView)view.findViewById(R.id.store_mun);
            ImageView store_mum_image=(ImageView)view.findViewById(R.id.store_mum_image);
            store_mum_image.setImageResource(storeClass.getImageId());
            store_mum.setText(storeClass.getName());
            return view;
        }
    }
}
