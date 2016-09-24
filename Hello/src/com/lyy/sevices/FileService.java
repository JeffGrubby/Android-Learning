package com.lyy.sevices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileService {
	private Context context;
	
	public FileService(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	/**
	 * 保存
	 * @param name 文件名称
	 * @param content 文件内容
	 * @throws FileNotFoundException 
	 */
	public void save(String name,String content) throws FileNotFoundException,IOException {
		
		FileOutputStream outputStream =context.openFileOutput(name, Context.MODE_PRIVATE);
		outputStream.write(content.getBytes());
		outputStream.close();
	}
	
	/**
	 * 
	 * @param name 文件名称
	 * @return	读取信息
	 * @throws IOException
	 */
	
	public String read(String name) throws Exception {
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String filepath = Environment.getExternalStorageDirectory().
					getCanonicalFile()+name;
			Log.d("filepath", filepath);
			File file = new File(filepath);
			if (file.exists()) {
				FileInputStream inputStream= new FileInputStream(file);
//				FileInputStream inputStream = context.openFileInput(filepath);
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] b = new byte[1024];
				int len =0;
				while((len = inputStream.read(b))!=-1){
					outStream.write(b, 0, len);
				}
				byte[] result = outStream.toByteArray();
				outStream.close();
				inputStream.close();
				
				return new String(result);
				}
			} 	
		
		return null;
	}
	
	/**
	 * 保存
	 * @param name 文件名称
	 * @param content 文件内容
	 * @throws FileNotFoundException 
	 */
	public void saveToSDcard(String name,String content) throws Exception{
		Log.d("location", Environment.getExternalStorageDirectory().toString());
		File file = new File(Environment.getExternalStorageDirectory(), name);	
		FileOutputStream outputStream =new FileOutputStream(file);
		outputStream.write(content.getBytes());
		outputStream.close();
	}
	
}
