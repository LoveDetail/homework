package com.gp.homework.pattern.proxy;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyJavaTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Object pro = new JDKindergarten().newInstance(new FatherTom()) ;
		
		System.out.println(System.currentTimeMillis());
		Method method = null ;
		
		method = pro.getClass().getMethod("takeCareOfBaby", new Class<?>[] {String.class,String.class}) ;
		
		method.invoke(pro, new Object[] {"猕猴桃","小海马"}) ;
		
		
		System.out.println(System.currentTimeMillis());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		try {
//			Object obj = new JDKindergarten().newInstance(new FatherTom()) ;
//			Method method = obj.getClass().getMethod("takeCareOfBaby",new Class<?>[] {String.class,String.class}) ;
//			
//			method.invoke(obj, new Object[] {"香蕉和火龙果","小海马"}) ;
//		}
//		catch (IllegalAccessException |  InvocationTargetException | NoSuchMethodException | SecurityException e) {
//			e.printStackTrace() ;
//		}
		
	}
}
