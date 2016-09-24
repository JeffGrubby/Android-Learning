package com.lyy.hello;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MsgTestActivity extends Activity {
	private EditText inputOne;
	private EditText inputTwo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg_test);
		
		inputOne = (EditText)this.findViewById(R.id.input_one);
		inputTwo = (EditText)this.findViewById(R.id.input_two);
		Button button = (Button)this.findViewById(R.id.Message_Send);
		button.setOnClickListener(new SendListener());
	}
	
	
	private final class SendListener  implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String number = inputOne.getText().toString();
			String content = inputTwo.getText().toString();
			SmsManager sm = SmsManager.getDefault();
			ArrayList<String> s= sm.divideMessage(content);
			for (String string : s) {
				sm.sendTextMessage(number, null, string, null, null);
			}
			Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
		}
		
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_test, menu);
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
