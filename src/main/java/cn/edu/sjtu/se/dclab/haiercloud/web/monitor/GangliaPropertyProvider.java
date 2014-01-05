package cn.edu.sjtu.se.dclab.haiercloud.web.monitor;

//package cn.edu.sjtu.se.dclab.ganglia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cn.edu.sjtu.se.dclab.haiercloud.web.util.XMLHelper;

public class GangliaPropertyProvider {

	private StreamProvider streamProvider;

	private String metricString = "";

	public GangliaPropertyProvider(StreamProvider streamProvider) {
		this.streamProvider = streamProvider;
	}

	/**
	 * Get data through http request
	 * 
	 * @return a collection of Metrics
	 * 
	 * @throws SystemException
	 *             if unable to get metrics
	 */
	public VMStatus getMetrics(String hostname) throws Exception {
		// contains all status info
		VMStatus status = new VMStatus();

		// get specification
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resource = appContext.getResource("classpath:metrics.xml");
		InputStream is = resource.getInputStream();
		XMLHelper helper = new XMLHelper(is);
		Set<String> metrics = helper.getMetrics();
		// get connection url
		String spec = helper.getSpec(hostname);
		// read stream
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					streamProvider.readFrom(spec)));

			String line = reader.readLine();
			if (line != null) {
				status.getInfoFromGanglia(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return status;
	}
}