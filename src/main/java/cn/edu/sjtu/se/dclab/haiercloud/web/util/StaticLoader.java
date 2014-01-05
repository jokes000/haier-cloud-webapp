package cn.edu.sjtu.se.dclab.haiercloud.web.util;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class StaticLoader {

	public static String getPathByClasspath(String name) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resource = appContext.getResource("classpath:" + name);
		String path = null;
		try {
			path = resource.getURI().getPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}
}
