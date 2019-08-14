package com.gp.homework.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKindergarten_k implements InvocationHandler{
	
	Object target ;
	
	public Object JDKindergarten_k(Object obj) throws NoSuchMethodException, SecurityException {
		this.target = obj ;
		Class<?> clazz = obj.getClass() ;
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this) ;
	}
	

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		return method.invoke(target, args);
	}
	
	
	
	
	
}
