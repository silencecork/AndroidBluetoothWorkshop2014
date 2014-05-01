package com.example.bluetootharminno;

import java.util.UUID;

import com.android.utility.bluetooth.BluetoothConnectionHelper;
import com.android.utility.bluetooth.OnBluetoothMessageListener;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ClientConnectionActivity extends Activity {
    
    private static final String APP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
//    private static final String APP_UUID = "8778e27e-564b-4c9c-94fb-4b8138ee61f0";
    private BluetoothConnectionHelper mHelper;
    private TextView mReceivedText;
    private String mCurrentText = "";
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.connection_activity);
        
        Button ledButton = (Button) findViewById(R.id.btn_led);
        ledButton.setOnClickListener(mLedControlClickListener);
        
        Button servoLeft = (Button) findViewById(R.id.btn_servo_left);
        servoLeft.setOnClickListener(mServoLeftClickListener);
        
        Button servoCenter = (Button) findViewById(R.id.btn_servo_center);
        servoCenter.setOnClickListener(mServoCenterClickListener);
        
        Button servoRight = (Button) findViewById(R.id.btn_servo_right);
        servoRight.setOnClickListener(mServoRightClickListener);
        
        Button playOne = (Button) findViewById(R.id.btn_play_one);
        playOne.setOnClickListener(mPlaySongOne);
        
        Button playTwo = (Button) findViewById(R.id.btn_play_two);
        playTwo.setOnClickListener(mPlaySongTwo);
        
        Button playThree = (Button) findViewById(R.id.btn_play_three);
        playThree.setOnClickListener(mPlaySongThree);
        
        mReceivedText = (TextView) findViewById(R.id.content);
        
        mScrollView = (ScrollView) findViewById(R.id.content_scroller);
        
        BluetoothDevice device = getIntent().getParcelableExtra("device");
        mHelper = BluetoothConnectionHelper.createClient(UUID.fromString(APP_UUID), device);
        mHelper.setMessageReceiver(mListener);
        mHelper.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHelper != null) {
            mHelper.close();
        }
    }
    
    private OnClickListener mLedControlClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mHelper.isConnect()) {
               mHelper.sendMessage("$c000");
            }
        }
    };
    
    private OnClickListener mServoLeftClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mHelper.isConnect()) {
               mHelper.sendMessage("$h200");
            }
        }
    };
    
    private OnClickListener mServoCenterClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mHelper.isConnect()) {
               mHelper.sendMessage("$h500");
            }
        }
    };
    
    private OnClickListener mServoRightClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mHelper.isConnect()) {
               mHelper.sendMessage("$h800");
            }
        }
    };
    
    private OnClickListener mPlaySongOne = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mHelper.isConnect()) {
               mHelper.sendMessage("$m000");
            }
        }
    };
    
    private OnClickListener mPlaySongTwo = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mHelper.isConnect()) {
               mHelper.sendMessage("$m100");
            }
        }
    };
    
    private OnClickListener mPlaySongThree = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mHelper.isConnect()) {
               mHelper.sendMessage("$m200");
            }
        }
    };
    
    private OnBluetoothMessageListener mListener = new OnBluetoothMessageListener() {
        @Override
        public void onMessageReceived(BluetoothDevice device, String message) {
            if (message != null) {
                mCurrentText += message;
                mReceivedText.setText(mCurrentText);
            }
            mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
        }
        @Override
        public void onDisconnect(BluetoothDevice device) {
            Toast.makeText(ClientConnectionActivity.this, "Disconnect", Toast.LENGTH_LONG).show();
            finish();
        }
        @Override
        public void onConnected(BluetoothDevice device) {
            Toast.makeText(ClientConnectionActivity.this, "Connect", Toast.LENGTH_LONG).show();
        }
    };

}