package cn.edu.sjtu.se.dclab.haiercloud.web.util;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLHelper {
	
	private Document doc;
	private Set<String> metrics;
	private String ip;
	
	public XMLHelper(InputStream in) {
		SAXReader reader = new SAXReader();
        try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        
        metrics = new HashSet<String>();
        
        Element root = doc.getRootElement();
        for (Iterator iter = root.elementIterator("ip"); iter.hasNext(); ) {
			Element node = (Element) iter.next();
			ip = node.getTextTrim();
		}
        
        for (Iterator iter = root.elementIterator("metric"); iter.hasNext();) {
			Element node = (Element) iter.next();
			String name = node.elementText("name");
			metrics.add(name);
        }
	}
	
	public Set<String> getMetrics() {
		return metrics;
	}

	public void setMetrics(Set<String> metrics) {
		this.metrics = metrics;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSpec(String hostname) {
		Element root = doc.getRootElement();
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		
		for (Iterator iter = root.elementIterator("ip"); iter.hasNext(); ) {
			Element node = (Element) iter.next();
			sb.append(node.getTextTrim());
		}
		
		sb.append("/ganglia/nagios/check_multiple_metrics_warn.php?host=");
		sb.append(hostname);
		sb.append("&checks=");
		
		/*
		 * <metric>
				<name>disk_free</name>
				<operator>less</operator>
				<critical_value>10</critical_value>
			</metric>
		 */
		for (Iterator iter = root.elementIterator("metric"); iter.hasNext();) {
			Element node = (Element) iter.next();
			
			String name = node.elementText("name");
			String operator = node.elementText("operator");
			String critical_value = node.elementText("critical_value");
			
			sb.append(":" + name + "," + operator + "," + critical_value);
		}
		
		return sb.toString();
	}
}
