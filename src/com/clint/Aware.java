package com.clint;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import test.ServiceHello;

public class Aware implements ApplicationContextAware {

	//private ApplicationContext context;
	 public static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
		System.out.println("spring启动调用webservice启动");	
		new ServiceHello().returnWebService();
	}

}
