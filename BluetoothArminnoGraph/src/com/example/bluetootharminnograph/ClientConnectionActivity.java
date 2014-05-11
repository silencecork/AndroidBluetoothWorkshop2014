package com.example.bluetootharminnograph;

import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.utility.bluetooth.BluetoothConnectionHelper;
import com.android.utility.bluetooth.OnBluetoothMessageListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class ClientConnectionActivity extends Activity {

	private static final String APP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
	private BluetoothConnectionHelper mHelper;
	private String mReceivedText = "";
	private GraphViewSeries mDatas;
	private int mDataCount = 1;
	private GraphView mGraphView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.connection_activity);

		BluetoothDevice device = getIntent().getParcelableExtra("device");
		mHelper = BluetoothConnectionHelper.createClient(UUID.fromString(APP_UUID), device);
		mHelper.setMessageReceiver(mListener);
		mHelper.connect();

		GraphViewData[] list = new GraphViewData[10];
		for (int i = 0; i < list.length; i++) {
			list[i] = new GraphViewData(0, 0);
		}

		mDatas = new GraphViewSeries(list);

		mGraphView = new LineGraphView(this, "GraphViewDemo");
		mGraphView.addSeries(mDatas);
		mGraphView.setScrollable(true);
		mGraphView.setScalable(true);

		ViewGroup viewGroup = (ViewGroup) findViewById(R.id.graph_root);
		viewGroup.addView(mGraphView);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mHelper != null) {
			mHelper.close();
		}
	}

	private OnBluetoothMessageListener mListener = new OnBluetoothMessageListener() {
		@Override
		public void onMessageReceived(BluetoothDevice device, String message) {
			if (message != null) {
				mReceivedText += message;
				if (message.contains("\n")) {
					// parsing Sonar : 5cm
					if (mReceivedText.startsWith("Sonar")) {

						// remove space
						String filterText = mReceivedText.replaceAll("\\s", "");

						// find start
						int beginIndex = filterText.indexOf(":");

						// find end
						int endIndex = filterText.indexOf("cm");

						if (beginIndex >= 0 && endIndex >= 0 && endIndex > beginIndex + 1) {

							// get string about distance
							String result = filterText.substring(beginIndex + 1, endIndex);

							try {
								// cast to integer
								int distance = Integer.parseInt(result);

								// create graph
								mDatas.appendData(new GraphViewData(mDataCount, distance), true);

								// switch viewport
								mGraphView.setViewPort(0, mDataCount);

								// add count
								mDataCount++;

								// if too close to sonar, let led blink
								if (distance < 10 && mHelper.isConnect()) {
									mHelper.sendMessage("$c000");
								}
							} catch (Exception e) {
								Log.e("", "prepare error", e);
							}
						}
					}
					mReceivedText = "";
				}
			}
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