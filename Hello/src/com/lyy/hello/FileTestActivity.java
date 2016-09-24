package com.lyy.hello;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.lyy.sevices.FileService;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileTestActivity extends Activity {
	private EditText fileName;
	private EditText fileContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_test);
		
		fileName = (EditText)this.findViewById(R.id.input_three);
		fileContent = (EditText)this.findViewById(R.id.input_four);
		Button save = (Button)this.findViewById(R.id.save);
		save.setOnClickListener(new FileWriteListener());	
		
		Button read = (Button)this.findViewById(R.id.read);
		read.setOnClickListener(new FileReadListener());	
		
	}

	private final class FileWriteListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String name = fileName.getText().toString();
			String content = fileContent.getText().toString();
			FileService fileService = new FileService(getApplicationContext());
			try {
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
				fileService.saveToSDcard(name, content);
				Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast.makeText(getApplicationContext(), R.string.fail, Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
		
	}
	
	
	private final class FileReadListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String name = "/"+fileName.getText().toString();
			FileService fileService = new FileService(getApplicationContext());
			Log.d("filename", name);
			try {
				String s = fileService.read(name);
				Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.d("End","not found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getApplicationContext(), R.string.fail, Toast.LENGTH_LONG).show();
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_test, menu);
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
