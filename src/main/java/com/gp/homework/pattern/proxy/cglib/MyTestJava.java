package com.gp.homework.pattern.proxy.cglib;

import com.gp.homework.pattern.proxy.FatherTom;
import com.gp.homework.pattern.proxy.Person;
import com.gp.homework.pattern.proxy.myproxy.WayneCglibProxy;
import com.gp.homework.pattern.proxy.myproxy.WayneJDKProxy;

public class MyTestJava {
	
	public static void main(String[] args) {


		System.out.println(System.getProperty("line.separator"));


//		Person p1 = (Person)new WayneJDKProxy().newInstance(new FatherTom()) ;
//		p1.takeCareOfBaby("橙子", "小海马");
//
//		System.out.println("======================================================================");
//
//		Person p2 = new WayneCglibProxy<Person>().newInstance(FatherTom.class) ;
//		p2.takeCareOfBaby("orange", "tangdoudou");
		
		
	}

}
