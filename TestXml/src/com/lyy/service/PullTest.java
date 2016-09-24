package com.lyy.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.lyy.model.Person;

import android.util.Xml;

public class PullTest {
	
/**
 * 	
 * @param in 文件输入流
 * @return	Person类列表
 * @throws XmlPullParserException
 * @throws IOException
 */
	public static ArrayList<Person> pullFromXml(InputStream in) throws XmlPullParserException, IOException {
		//XmlPullParser声明定义
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(in, "UTF-8");
		//声明Persons列表和Person对象
		ArrayList<Person> persons = null;
		Person person = null;
		//获取xml文档的标记类型 返回int值
		int tag = pullParser.getEventType();
		//循环读取 记录到Person 添加Person到Persons列表
		while(tag!=XmlPullParser.END_DOCUMENT){
			switch (tag) {
			case XmlPullParser.START_DOCUMENT:
				persons = new ArrayList<Person>();	
				break;
			
			case XmlPullParser.START_TAG:
				if ("Person".equals(pullParser.getName())) {
					person = new Person();
					int id = Integer.parseInt(pullParser.getAttributeValue(0));
					person.setId(id);
				}
				if ("name".equals(pullParser.getName())) {
					person.setName(pullParser.nextText());
				}
				if ("sex".equals(pullParser.getName())) {
					person.setSex(pullParser.nextText());
				}
				break;
				
			case XmlPullParser.END_TAG:		
				if ("Person".equals(pullParser.getName())) {
					persons.add(person);		
				}
				break;
				
			default:
				break;
			}
			//获取下一个标记
		tag = pullParser.next();
		}
		
		return persons;
	}
	

	public PullTest() {
		// TODO Auto-generated constructor stub
	}
}
