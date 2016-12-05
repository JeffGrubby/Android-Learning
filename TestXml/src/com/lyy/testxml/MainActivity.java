package com.lyy.testxml;

import com.lyy.test.TestXml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private TextView content;
	private TestXml test;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		test = new TestXml();
		
		content = (TextView)findViewById(R.id.title);
		Button read = (Button)findViewById(R.id.read);
		Button write = (Button)findViewById(R.id.write);
		read.setOnClickListener(this);
		write.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()) {
		case R.id.read:
			try {
				test.test();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content.setText("read from xml");
			break;
		case R.id.write:
			try {
				test.test_two();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content.setText("push file as xml");
			break;
		default:
			break;
		}
		
		
	}
}
