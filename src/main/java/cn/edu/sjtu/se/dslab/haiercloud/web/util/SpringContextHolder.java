package cn.edu.sjtu.se.dslab.haiercloud.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {

	// properties
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = arg0;
	}
	
	public Object getBean(Class<?> requiredType) {
		return applicationContext.getBean(requiredType);
	}

}
