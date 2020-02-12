package com.laimilife.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

public final class SpringBeanUtil extends ApplicationObjectSupport {  
	private static ApplicationContext applicationContext = null;  
	
	@Override  
	protected void initApplicationContext(ApplicationContext context) throws BeansException {  
		super.initApplicationContext(context);  
		if(SpringBeanUtil.applicationContext == null){  
		    SpringBeanUtil.applicationContext = context;  
		      System.out.println("========ApplicationContext配置成功========");  
		}  
	} 
	public static ApplicationContext getAppContext() {  
		return applicationContext;  
	}  
	public static Object getBean(String name){  
	    return getAppContext().getBean(name);  
	} 
}  
