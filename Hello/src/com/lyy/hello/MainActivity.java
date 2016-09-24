package com.lyy.hello;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText e;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e = (EditText)findViewById(R.id.Input);
		
		Button a =(Button)findViewById(R.id.viewMsg);
		a.setOnClickListener(new MessageButtonListener());
		
		Button b = (Button)findViewById(R.id.button);
		b.setOnClickListener(new InputButtonListener());
		
		Button c =(Button)findViewById(R.id.viewFile);
		c.setOnClickListener(new FileButtonListener());
	}
	
	
	private final class InputButtonListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String number  = e.getText().toString();
			if(number.length()==0||null == number) return ;
			Intent intent = new Intent();
			intent.setAction("android.intent.action.CALL");
//			intent.addCategory("android.intent.category.DEFAULT");
			intent.setData(Uri.parse("tel:"+number));
			startActivity(intent);
		}
			
	}
	
	
	private final class  MessageButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this,MsgTestActivity.class);
			startActivity(intent);
		}
			
	}
	
	
	private final class  FileButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d("Start", "OnClickForFile");
			Intent intent = new Intent(MainActivity.this,FileTestActivity.class);
			startActivity(intent);
		}
			
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
}
