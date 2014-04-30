package com.example.toastexample;

import com.example.linearlayoutlogin.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
				} else {
					
					// TODO
					showError(strUserName, strPassword);
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
	
	// TODO
	protected void showError(String strUserName, String strPassword) {
		
		// 顯示Toast
		if (strUserName.equals("")) {
			Toast.makeText(this, R.string.username_empty, Toast.LENGTH_LONG).show();
		} else if (strPassword.equals("")) {
			Toast.makeText(this, R.string.password_empty, Toast.LENGTH_LONG).show();
		}
	}

	private void loginComplete() {
		
		TextView notify = (TextView) findViewById(R.id.title);
		
		// 更改文字
		notify.setText(R.string.complete);
		
		Button finishButton = (Button) findViewById(R.id.btn_finish);
		
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
