package com.example.listvieweventexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.simpleadapterlistviewexample.R;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
	
	String[] mKeys = {"icon", "title"};
	int[] mMappingViews = {R.id.icon, R.id.title};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.list);
		
		List<HashMap<String, ?>> list = getSampleData();
		
		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item, mKeys, mMappingViews);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(mTouchListener);
		listView.setOnScrollListener(mScrollListener);
	}
	
	private OnItemClickListener mTouchListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			TextView text = (TextView) v.findViewById(R.id.title);
			Toast.makeText(MainActivity.this, text.getText().toString(), Toast.LENGTH_SHORT).show();
		}
		
	};
	
	private OnScrollListener mScrollListener = new OnScrollListener() {

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalCount) {
			
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollstate) {
			if (scrollstate == SCROLL_STATE_FLING) {
				Toast.makeText(MainActivity.this, "Fling", Toast.LENGTH_SHORT).show();
			} else if (scrollstate == SCROLL_STATE_TOUCH_SCROLL) {
				Toast.makeText(MainActivity.this, "Scroll", Toast.LENGTH_SHORT).show();
			} else if (scrollstate == SCROLL_STATE_IDLE) {
				Toast.makeText(MainActivity.this, "Idle", Toast.LENGTH_SHORT).show();
			}
		}
		
	};
	
	
	private List<HashMap<String, ?>> getSampleData() {
		List<HashMap<String, ?>> list = new ArrayList<HashMap<String, ?>>(); 
		
		
		for (int i = 0; i < 5; i++) {
			HashMap map = new HashMap();
			map.put(mKeys[0], R.drawable.twitter);
			map.put(mKeys[1], "Twitter");
			list.add(map);
			
			map = new HashMap();
			map.put(mKeys[0], R.drawable.blogger);
			map.put(mKeys[1], "Blogger");
			list.add(map);
			
			map = new HashMap();
			map.put(mKeys[0], R.drawable.facebook);
			map.put(mKeys[1], "Facebook");
			list.add(map);
			
			map = new HashMap();
			map.put(mKeys[0], R.drawable.youtube);
			map.put(mKeys[1], "Youtube");
			list.add(map);
			
			map = new HashMap();
			map.put(mKeys[0], R.drawable.flickr);
			map.put(mKeys[1], "Flickr");
			list.add(map);
		}
		return list;
	}


}
