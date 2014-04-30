package com.android.demo.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }
	
	private void calcResult() {
		EditText inputHeight = (EditText) findViewById(R.id.input_height);
		EditText inputWeight = (EditText) findViewById(R.id.input_weight);
		String strHeight = inputHeight.getText().toString();
		String strWeight = inputWeight.getText().toString();
		float height = Float.parseFloat(strHeight) / 100;
		float weight = Float.parseFloat(strWeight);
		float BMI = weight / (height * height);

		TextView result = (TextView) findViewById(R.id.result);
		String strResult = "Your BMI is " + BMI + "\n";
		if (BMI > 24) {
			strResult += getString(R.string.advice_heavy);
		} else if (BMI < 18.5) {
			strResult += getString(R.string.advice_light);
		} else {
			strResult += getString(R.string.advice_average);
		}
		result.setText(strResult);
	}
}