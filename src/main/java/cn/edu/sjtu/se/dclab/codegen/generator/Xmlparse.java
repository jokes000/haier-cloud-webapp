package cn.edu.sjtu.se.dclab.codegen.generator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Xmlparse {
		
	public Map<String, Map<String, String>> parse(String f)
	{
		Map<String, Map<String, String>> params = new HashMap<String, Map<String,String>>();
		Map<String, String> param = new HashMap<String,String>();
		
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(f));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element root = document.getRootElement();
		String name = root.elementText("name");
		//System.out.println(name);
		Element cols = root.element("columnlist");
		for(Iterator i = cols.elementIterator(); i.hasNext();)
		{
			Element col = (Element)i.next();
			String colname = col.elementText("name");
			String type = new String();
			if("BIGINT".equals(col.elementTextTrim("type")))
				type = "int";
			else {
				type = "String";
			}
			param.put(colname, type);
			//System.out.println("**" + n);
		}
		params.put(name, param);
		return params;
	}
	
//	public static void main(String [] args) throws MalformedURLException, DocumentException
//	{
//		Map<String, Map<String, String>> ret = new HashMap<>();
//		String f = "./meta.xml";
//		Xmlparse xml = new Xmlparse();
//	    ret = xml.parse(f);
//		for(String key : ret.keySet())
//		{
//			Map<String, String> names = (Map<String, String>) ret.get(key);
//			for(String k : names.keySet())
//			{
//				String n = (String)names.get(k);
//				System.out.println("name:" + k);
//				System.out.println("type:" + n);
//			}
//		}
//	}
}