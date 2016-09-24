package com.lyy.test;

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
	
}
