package com.example.qdk.qdk_recept20.Activity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qdk.qdk_recept20.R;

/**
 * Created by lenovo on 2017/3/22.
 */

public class NfcActivity extends Activity{
    NfcAdapter mNfcAdapter;
    ImageView imageView;
    TextView mInfoText;
    Button button;
    String codeUse;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfcactivity_layout);
        button=(Button)findViewById(R.id.ok_nfc);
        button.setVisibility(View.INVISIBLE);
        imageView=(ImageView)findViewById(R.id.image_nfc);
        imageView.setImageResource(R.drawable.loading);
        mInfoText=(TextView)findViewById(R.id.hint_nfc);
        mInfoText.setText("请将手机靠近NFC设备");
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(NfcActivity.this,"NFC is not available on this device.",Toast.LENGTH_LONG).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goodDate=codeUse;
//                String goodDate="001G23020200182000002.00R31070300247000003.00";
                Intent intent=new Intent(NfcActivity.this,GoodsActivity.class);
//                if(goodDate==""){
//                    startActivity(intent);
//                }else {
//                    intent.putExtra("extra_data", goodDate);
//                    LogMainActivity.d("FragContainActivity", goodDate);
//                    startActivity(intent);
//                }
                intent.putExtra("extra_data", goodDate);
                Log.d("FragContainActivity", goodDate);
                startActivity(intent);


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Log.d("FragContainActivity","OK");
            processIntent(getIntent());
        }
    }

    @Override
    /**
    调用startActivity方法时传递的intent参数会作为onNewIntent方法的参数被使用，而Activity类本身通过getIntent方法返回的intent变量则是初始的intent值，
    从onNewIntent方法无关，除非调用startActivity之前通过setIntent设置intent，以保持intent总是最新的intent
     */
    public void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String code = new String(msg.getRecords()[0].getPayload());
        Log.d("FragContainActivity", code);

        //mInfoText.setText(new String(msg.getRecords()[0].getPayload())+"OK");
        mInfoText.setText("收到商品信息");
        imageView.setImageResource(R.drawable.ok_image);
        button.setVisibility(View.VISIBLE);

        int codeLength = code.length();
        Log.d("FragContainActivity", codeLength + "");
        codeUse = code.substring(1, codeLength - 1);
        Log.d("FragContainActivity", codeUse);
        Log.d("FragContainActivity", "fine");
    }

    protected void onRestart(){
        super.onRestart();
        Log.d("FragContainActivity","int to restart");
        finish();
    }

    protected void onDestroy(){
        Log.d("FragContainActivity","int to destroy");
        super.onDestroy();
    }

}
