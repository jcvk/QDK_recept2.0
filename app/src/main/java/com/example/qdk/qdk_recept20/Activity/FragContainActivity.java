package com.example.qdk.qdk_recept20.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qdk.qdk_recept20.Fragment.Fragment_four;
import com.example.qdk.qdk_recept20.Fragment.Fragment_one;
import com.example.qdk.qdk_recept20.Fragment.Fragment_three;
import com.example.qdk.qdk_recept20.Fragment.Fragment_two;
import com.example.qdk.qdk_recept20.R;

/**
 * Created by lenovo on 2017/3/21.
 */

public class FragContainActivity extends FragmentActivity implements OnClickListener{
    private Fragment_one fg1;
    private Fragment_two fg2;
    private Fragment_three fg3;
    private Fragment_four fg4;

    private RelativeLayout one_layout;
    private RelativeLayout two_layout;
    private RelativeLayout three_layout;
    private RelativeLayout four_layout;

    private ImageView image_buy;
    private ImageView image_ticket;
    private ImageView image_integral;
    private ImageView image_me;

    private int whirt=0xFFFFFFFF;
    private int gray=0xFF7597B3;
    private int blue=0xFF0AB2FB;

    private TextView text_buy;
    private TextView text_ticket;
    private TextView text_integral;
    private TextView text_me;



    FragmentManager fManager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        fManager=getSupportFragmentManager();
        initMainView();
        initViews();
    }

    public void initMainView(){
        FragmentTransaction transaction=fManager.beginTransaction();
        fg1=new Fragment_one();
        transaction.add(R.id.content,fg1);
        transaction.commit();
    }

    public void initViews(){
        one_layout=(RelativeLayout)findViewById(R.id.one_layout);
        two_layout=(RelativeLayout)findViewById(R.id.two_layout);
        three_layout=(RelativeLayout)findViewById(R.id.three_layout);
        four_layout=(RelativeLayout)findViewById(R.id.four_layout);
        image_me=(ImageView)findViewById(R.id.me_image);
        image_ticket=(ImageView)findViewById(R.id.ticket_image);
        image_buy=(ImageView)findViewById(R.id.buy_image);
        image_integral=(ImageView)findViewById(R.id.integral_image);
        text_buy=(TextView)findViewById(R.id.buy_text);
        text_me=(TextView)findViewById(R.id.me_text);
        text_integral=(TextView)findViewById(R.id.integral_text);
        text_ticket=(TextView)findViewById(R.id.ticket_text);
        Log.d("FragContainActivity","进入了initViews");
        one_layout.setOnClickListener(this);
        two_layout.setOnClickListener(this);
        three_layout.setOnClickListener(this);
        four_layout.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.one_layout:
                setChoseItem(1);
                Log.d("FragContainActivity","进入了onClick分支");
                break;
            case R.id.two_layout:
                setChoseItem(2);
                break;
            case R.id.three_layout:
                setChoseItem(3);
                break;
            case R.id.four_layout:
                setChoseItem(4);
                break;
        }

    }

    public void setChoseItem(int index){
        FragmentTransaction transaction=fManager.beginTransaction();
        clearChoice();
        hideFragments(transaction);
        switch (index){
            case 1:
                image_buy.setImageResource(R.drawable.buy_focus);
                text_buy.setTextColor(blue);
//                one_layout.setBackgroundColor(blue);
                if(fg1==null){
                    fg1=new Fragment_one();
                    transaction.add(R.id.content,fg1);//这里就直接添加了
                }else{
                    transaction.show(fg1);
//                    transaction.replace(R.id.content,fg1);
                }
                break;
            case 2:
                image_ticket.setImageResource(R.drawable.tickt_focus);
                text_ticket.setTextColor(blue);
//                two_layout.setBackgroundColor(blue);
                if(fg2==null){
                    fg2=new Fragment_two();
                    transaction.add(R.id.content,fg2);
                }else{
                    transaction.show(fg2);
//                    transaction.replace(R.id.content,fg2);
                }
                break;
            case 3:
                image_integral.setImageResource(R.drawable.integral_foucs);
                text_integral.setTextColor(blue);
//                three_layout.setBackgroundColor(blue);
                if(fg3==null){
                    fg3=new Fragment_three();
                    transaction.add(R.id.content,fg3);
                }else{
                    transaction.show(fg3);
//                    transaction.replace(R.id.content,fg3);
                }
                break;
            case 4:
                image_me.setImageResource(R.drawable.me_focus);
                text_me.setTextColor(blue);
//                four_layout.setBackgroundColor(blue);
                if(fg4==null){
                    fg4=new Fragment_four();
                    transaction.add(R.id.content,fg4);
                }else{
                    transaction.show(fg4);
//                    transaction.replace(R.id.content,fg4);
                }
                break;
        }
        transaction.commit();
    }

    public void hideFragments(FragmentTransaction transaction){
        if(fg1!=null){
            transaction.hide(fg1);
        }
        if(fg2!=null){
            transaction.hide(fg2);
        }
        if(fg3!=null){
            transaction.hide(fg3);
        }
        if(fg4!=null){
            transaction.hide(fg4);
        }
    }

    public void clearChoice(){
        Log.d("FragContainActivity","into clearChoice");
        image_buy.setImageResource(R.drawable.buy_unfocus);
        Log.d("FragContainActivity","123");
        one_layout.setBackgroundColor(whirt);
        text_buy.setTextColor(gray);
        image_ticket.setImageResource(R.drawable.ticket_unfocus);
        two_layout.setBackgroundColor(whirt);
        text_ticket.setTextColor(gray);
        image_integral.setImageResource(R.drawable.integral_unfocus);
        three_layout.setBackgroundColor(whirt);
        text_integral.setTextColor(gray);
        image_me.setImageResource(R.drawable.me_unfocus);
        four_layout.setBackgroundColor(whirt);
        text_me.setTextColor(gray);
        Log.d("FragContainActivity","finish clearChoice");
    }
}
