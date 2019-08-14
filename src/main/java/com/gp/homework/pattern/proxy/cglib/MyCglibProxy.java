package com.gp.homework.pattern.proxy.cglib;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class MyCglibProxy implements MethodInterceptor{
	Log logger ;
	
	public Object newInstance(Class<?> clazz) {
		logger = LogFactory.getLog(clazz) ;
		
		Enhancer enhancer = new Enhancer() ;
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);

		return enhancer.create() ;
	}
	
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		before() ;
		Object obj = arg3.invokeSuper(arg0, arg2) ;
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
