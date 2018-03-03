package com.example.qdk.qdk_recept20.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qdk.qdk_recept20.R;

/**
 * Created by lenovo on 2017/3/25.
 */

public class LogMainActivity extends Activity {

    Button buttonLog;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_layout);
        buttonLog=(Button)findViewById(R.id.log);
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LogMainActivity.this,FragContainActivity.class);
                startActivity(intent);
            }
        });
    }
}
