package com.example.loginexample;

import com.example.linearlayoutlogin.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 設定畫面
		setContentView(R.layout.activity_main);
		
		// 找到按鈕
		Button loginButton = (Button) findViewById(R.id.btn_ok);
		Button cancelButton = (Button) findViewById(R.id.btn_cancel);
		
		// 建立Listener負責接收事件
		OnClickListener loginListener = new OnClickListener() {

			@Override
			public void onClick(View v) {

				EditText username = (EditText) findViewById(R.id.username);
				EditText password = (EditText) findViewById(R.id.password);
				
				// 取得帳號密碼
				String strUserName = username.getText().toString();
				String strPassword = password.getText().toString();
				
				// 檢查輸入內容
				if (!strUserName.equals("") && !strPassword.equals("")) {
					loginComplete();
				}
				
			}
			
		};
		
		OnClickListener cancelListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText username = (EditText) findViewById(R.id.username);
				EditText password = (EditText) findViewById(R.id.password);
				username.setText("");
				password.setText("");
			}
			
		};
		
		// 設定listener給button
		loginButton.setOnClickListener(loginListener);
		cancelButton.setOnClickListener(cancelListener);
		
	}
	
	private void loginComplete() {
		
		TextView notify = (TextView) findViewById(R.id.title);
		
		// 更改文字
		notify.setText(R.string.complete);
		
		Button finishButton = (Button) findViewById(R.id.btn_finish);
		
		// ?�改?�件顯示
		finishButton.setVisibility(View.VISIBLE);
		finishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 呼叫finish結束
				finish();
			}
			
		});
	}

}
