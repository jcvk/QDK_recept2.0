package com.example.qdk.qdk_recept20.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.Activity.TallyCastActivity;
import com.example.qdk.qdk_recept20.R;
import com.example.qdk.qdk_recept20.Class.StoreClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/15.
 */

public class Fragment_one extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;//图片展示
    private ImageView[] tips;//存放点点
    public ImageView[] mImageViews;//存放图片
    private int[] imaIdArray;//图片id
    private List<StoreClass> storeClassList =new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.frag_one_layout,container,false);
        initStoreList(view);
        initAdv(view);
        return view;
    }

    private void initStoreList(View view){
        StoreClass li_sheng=new StoreClass("利生超市","卫龙辣条0.35元一包",R.drawable.dongda);
        StoreClass xue_jia=new StoreClass("学佳超市","矿泉水0.9元一瓶",R.drawable.dongdagouwu);
        StoreClass dong_da=new StoreClass("东大商店","好丽友蛋糕10元一袋",R.drawable.huayuan);
        StoreClass hua_yuan=new StoreClass("华苑超市","蓝月亮洗衣液15元一包",R.drawable.huayuan);
        storeClassList.add(li_sheng);
        storeClassList.add(xue_jia);
        storeClassList.add(dong_da);
        storeClassList.add(hua_yuan);
        System.out.println(getActivity()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        StoreAdapter adapter=new StoreAdapter(getActivity(),R.layout.store_item, storeClassList);

        ListView listView=(ListView)view.findViewById(R.id.store_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String num;
                Intent intent=new Intent(getActivity(), TallyCastActivity.DiscountActivity.class);
                switch (position){
                    case 0:
                        num="0";
                        intent.putExtra("extra_num",num);
                        break;
                    case 1:
                        num="1";
                        intent.putExtra("extra_num",num);
                        break;
                    case 2:
                        num="2";
                        intent.putExtra("extra_num",num);
                        break;
                    case 3:
                        num="3";
                        intent.putExtra("extra_num",num);
                        break;
                }
                startActivity(intent);

            }
        });
    }

    private void initAdv(View view){
        ViewGroup group=(ViewGroup)view.findViewById(R.id.viewGroup);//展示小点点，在这个布局里面添加了一个在onCreate中创建的布局
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);//展示图片

        imaIdArray=new int[]{R.drawable.adver,R.drawable.adver2,R.drawable.adver3,R.drawable.adver4};

        tips=new ImageView[imaIdArray.length];
        for(int i=0;i<tips.length;i++){
            ImageView imageView=new ImageView(getActivity());//新创建一个图片，this表示在这个activity中创建
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));//这里是将屏幕化为一个区域这个区域高10，宽10
            tips[i]=imageView;//将创建的图片复制给tips
            if(i==0){
                tips[i].setBackgroundResource(R.drawable.foucs);
            }else{
                tips[i].setBackgroundResource(R.drawable.unfoucs);
            }//这就是最开始要有一个状态
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin=5;
            layoutParams.rightMargin=5;
            group.addView(imageView,layoutParams);//这里创建了一个布局用来装每一个点点
        }

        mImageViews=new ImageView[imaIdArray.length];
        for(int i=0;i<mImageViews.length;i++){
            ImageView imageView=new ImageView(getActivity());//都是创建一个图片，然后将图片放进去，和之前的在图片布局中加图片不一样
            imageView.setBackgroundResource(imaIdArray[i]);
            mImageViews[i]=imageView;
        }

        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem((mImageViews.length)*100);
    }


    public class MyAdapter extends PagerAdapter {



        public int getCount(){
            return Integer.MAX_VALUE;
        }//获得当前窗体的界面数，无关紧要

        public boolean isViewFromObject(View arg0,Object arg1){
            return arg0==arg1;
        }//判断是否由对象生成界面

        public void destroyItem(ViewGroup container,int position,Object object){//从当前的View中移除对象
            container.removeView(mImageViews[position%mImageViews.length]);

        }

        public Object instantiateItem(ViewGroup container,int position){//返回一个对象，决定在ViewPager中显示那个对象,position是每滑动一次就会+1
            container.addView(mImageViews[position%mImageViews.length],0);
            return mImageViews[position%mImageViews.length];


        }



    }

    public class StoreAdapter extends ArrayAdapter<StoreClass> {
        private int resourceID;

        public StoreAdapter(Context context, int textViewResourceID, List<StoreClass> objects){
            super(context,textViewResourceID,objects);
            resourceID=textViewResourceID;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            StoreClass storeClass =getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            ImageView storeImage=(ImageView)view.findViewById(R.id.store_image);
            TextView storeInfo=(TextView)view.findViewById(R.id.store_info);
            TextView storeInfo1=(TextView)view.findViewById(R.id.store_info1);
            storeImage.setImageResource(storeClass.getImageId());
            storeInfo.setText(storeClass.getName());
            storeInfo1.setText("今日特价\n"+ storeClass.getNews());
            return view;
        }
    }

    public void onPageScrollStateChanged(int arg0){

    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    public void onPageSelected(int arg0){
        setImageBackground(arg0%mImageViews.length);
    }

    private void setImageBackground(int selectItems){
        for (int i=0;i<tips.length;i++){
            if(i==selectItems){
                tips[i].setBackgroundResource(R.drawable.foucs);

            }else{
                tips[i].setBackgroundResource(R.drawable.unfoucs);

            }
        }
    }


}
