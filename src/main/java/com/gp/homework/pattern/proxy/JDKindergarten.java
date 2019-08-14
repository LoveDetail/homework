package com.gp.homework.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JDKindergarten implements InvocationHandler {

	Object target ;
	Log logger ;
	
	public Object newInstance(Object obj) {
		this.target = obj ;
		Class<?> clazz = obj.getClass() ;
		logger = LogFactory.getLog(obj.getClass()) ;
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this) ;
	}
	
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before() ;
		Object obj = method.invoke(this.target, args) ;
		after() ;
		return obj ;
	}

	private void before() {
		logger.info("=======start");
	}
	
	private void after() {
		logger.info("=======end");
	}
	
}
