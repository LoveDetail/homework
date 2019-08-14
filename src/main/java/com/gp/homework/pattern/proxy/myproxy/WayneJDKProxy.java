package com.gp.homework.pattern.proxy.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WayneJDKProxy implements InvocationHandler{

	Log logger ;
	Object target ;
	
	public Object newInstance(Object obj) {
		Class<?> clazz = obj.getClass() ;
		logger = LogFactory.getLog(clazz) ;
		this.target = obj ;
		return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(), this) ;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	
		before() ;
		Object obj = method.invoke(this.target, args) ;
		after() ;
		return obj;
	}
	
	
	private void before() {
		logger.info("=======start");
	}
	
	private void after() {
		logger.info("=======end");
	}
	
}
