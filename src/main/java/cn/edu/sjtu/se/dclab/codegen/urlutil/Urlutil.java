package cn.edu.sjtu.se.dclab.codegen.urlutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class Urlutil {
	private static final String UTIL_POST = "POST";
	private static final String UTIL_GET = "GET";
	private static final String UTIL_DELETE = "DELETE";
	private static final String UTIL_PUT = "PUT";
	
	private static String prepareParam(Map<String , Object> param)
	{
		StringBuffer sb = new StringBuffer();
		if(param.isEmpty())
		{
			return "";
		}
		else {
			for(String key : param.keySet())
			{
				String value = (String)param.get(key);
				if(sb.length() < 1)
				{
					sb.append(key).append("=").append(value);
				}else {
					sb.append("&").append(key).append("=").append(value);
				}
			}
		}
		return sb.toString();
	}
	
	public static String util_post(String urlStr, Map<String , Object> param) throws IOException
	{
		//String paramStr = param;
		String paramStr = prepareParam(param);
//		if(paramStr.length() > 0)
//		{
//			urlStr += "?" + paramStr;
//		}
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
	
		con.setRequestMethod(UTIL_POST);
		con.setDoInput(true);
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(paramStr.toString().getBytes("utf-8"));
		os.flush();
		os.close();
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		String result = "";
		while((line = buf.readLine()) != null)
		{
			result += "\n" + line;
		}
		buf.close();
		return result;
	}
	
	public static String util_get(String urlStr, Map<String , Object> param) throws IOException
	{
		//String paramStr = param;
		String paramStr = prepareParam(param);
		if(paramStr.length() > 0)
		{
			urlStr += "?" + paramStr;
		}
		URL url = new URL(urlStr);
		//System.out.println(urlStr.toString());
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod(UTIL_GET);
		con.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
		con.connect();
		BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		String result = "";
		while((line = buf.readLine()) != null)
		{
			result += "\n" + line;
		}
		buf.close();
		return result;
	}
	
	public static String util_delete(String urlStr, Map<String , Object> param) throws IOException
	{
		String paramStr = prepareParam(param);
//		if(paramStr.length() > 0)
//		{
//			urlStr += "?" + paramStr;
//		}
		URL url = new URL(urlStr);
		//System.out.println(urlStr.toString());
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		//con.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
		con.setRequestMethod(UTIL_DELETE);
		con.connect();
		BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		String result = "";
		while((line = buf.readLine()) != null)
		{
			result += "\n" + line;
		}
		buf.close();
		return result;				
	}
	
	public static String util_put(String urlStr, Map<String , Object> param) throws IOException
	{
		String paramStr = prepareParam(param);
//		if(paramStr.length() > 0)
//		{
//			urlStr += "?" + paramStr;
//		}
		URL url = new URL(urlStr);
		System.out.println(urlStr.toString());
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestMethod(UTIL_PUT);
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(paramStr.toString().getBytes("utf-8"));
		os.flush();
		os.close();
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		String result = "";
		while((line = buf.readLine()) != null)
		{
			result += "\n" + line;
		}
		buf.close();
		return result;				
	}	
}