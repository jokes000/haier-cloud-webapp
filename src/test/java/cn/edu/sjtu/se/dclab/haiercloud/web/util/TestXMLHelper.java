package cn.edu.sjtu.se.dclab.haiercloud.web.util;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import static org.junit.Assert.*;

public class TestXMLHelper{
	
	@Test
	public void TestGetSpec() {
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resource = appContext.getResource("classpath:metrics.xml");
		
		try {
			InputStream is = resource.getInputStream();
			XMLHelper helper = new XMLHelper(is);
			
			String needed = helper.getSpec("hadoop246.local");
			System.out.println(needed);
			String provided = "http://192.168.1.246/ganglia/nagios/check_multiple_metrics_warn.php?host=hadoop246.local&checks=:disk_free,less,10:mem_free,less,10:cpu_idle,less,10";
		
			assertEquals(needed, provided);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
