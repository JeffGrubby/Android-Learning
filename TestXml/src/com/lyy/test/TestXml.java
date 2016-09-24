package com.lyy.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.lyy.model.Person;
import com.lyy.service.PullTest;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestXml extends AndroidTestCase {

	
	public void test() throws Exception{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Person.xml");
		ArrayList<Person> persons = PullTest.pullFromXml(inputStream);
		for (Person person : persons) {
			Log.d("Person", person.toString());
		}	
	}
	
	
	public void test_two() throws Exception{
		File file = new File(this.getContext().getFilesDir(), "king.xml");
		FileOutputStream outputStream = new FileOutputStream(file);
		
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(new Person(01,"xi","boy"));
		persons.add(new Person(01,"yi","girl"));
		
		PullTest.pullFromBean(outputStream, persons);
		
	}
}
