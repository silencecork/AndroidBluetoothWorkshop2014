package com.example.listviewexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView mListView;
	
	private static final String[] NAMES = {"John", "Luke", "Matthew", "Peter", "James", 
		"John", "Luke", "Matthew", "Peter", "James", 
		"John", "Luke", "Matthew", "Peter", "James", 
		"John", "Luke", "Matthew", "Peter", "James"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListView = (ListView) findViewById(R.id.listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NAMES);
		mListView.setAdapter(adapter);
	}

}
