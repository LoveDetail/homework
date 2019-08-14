package com.gp.homework.pattern.proxy.myproxy;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class WayneCglibProxy<T> implements MethodInterceptor{
	private Log logger ;
	
	@SuppressWarnings("unchecked")
	public T newInstance(Class<?> clazz) {
		logger = LogFactory.getLog(clazz) ;
		
		Enhancer enhancer = new Enhancer() ;
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return (T)enhancer.create();
	}
	
	@Override
	public T intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		before() ;
		@SuppressWarnings("unchecked")
		T t = (T) arg3.invokeSuper(arg0, arg2) ;
		after() ;
		return t;
	}
	
	
	private void before() {
		logger.info("=======start");
	}
	
	private void after() {
		logger.info("=======end");
	}

	
}
