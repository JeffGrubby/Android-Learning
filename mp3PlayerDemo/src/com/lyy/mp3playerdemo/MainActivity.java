package com.lyy.mp3playerdemo;

import java.io.IOException;
import java.util.ArrayList;

import com.example.mp3playerdemo.R;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	private TextView name;
	private MediaPlayer player;
	private ArrayList<String> listAudio;
	private static int index;
	private static int max;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	player = MediaPlayer.create(getApplicationContext(), R.raw.bs);
		player = new MediaPlayer();
		name = (TextView)findViewById(R.id.name);
		Button play = (Button)findViewById(R.id.play);
		Button pause = (Button)findViewById(R.id.pause);
		Button last = (Button)findViewById(R.id.last);
		Button next = (Button)findViewById(R.id.next);
		Button stop = (Button)findViewById(R.id.stop);
		
		stop.setOnClickListener(this);
		next.setOnClickListener(this);
		last.setOnClickListener(this);
		pause.setOnClickListener(this);
		play.setOnClickListener(this);
		
		listAudio = new ArrayList<String>();
		String str[] = { MediaStore.Audio.Media._ID,
				MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.SIZE
		};
		
		
		Cursor cursor = this.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, str, null, null, null);
		
		Log.d("count",cursor.getCount()+"");
		TextView title = (TextView)findViewById(R.id.title);
		title.setText(title.getText().toString()+"we've got  "+cursor.getCount()+" songs!");
		
		while(cursor.moveToNext()){
			Log.d("id",cursor.getString(0));
			Log.d("name",cursor.getString(1));
			Log.d("path",cursor.getString(2));
			Log.d("size",cursor.getString(3));
			

			listAudio.add(cursor.getString(2));
			listAudio.add(cursor.getString(1));
		}
		
		max = listAudio.size();
		try {
			player.setDataSource(listAudio.get(0));
			player.prepare();
			name.setText(listAudio.get(index+1));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Override
	public void onClick(View arg0){
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.play:
			player.start();
			break;
		case R.id.pause:
			player.pause();
			break;
		case R.id.last:
			try {
				if (index>=2) {
					player.reset();
					index-=2;
					player.setDataSource(listAudio.get(index));
					player.prepare();
					name.setText(listAudio.get(index+1));
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.next:
			try {
				if (index<max-2) {
					player.reset();
					index+=2;
					player.setDataSource(listAudio.get(index));
					player.prepare();
					name.setText(listAudio.get(index+1));
				}		
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case R.id.stop:
			player.stop();
			break;
		default:
			break;
		}
		
		
	}
}
