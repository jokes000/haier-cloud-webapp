package cn.edu.sjtu.se.dclab.codegen.datatemplate;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sjtu.se.dclab.codegen.urlutil.Urlstr;
import cn.edu.sjtu.se.dclab.codegen.urlutil.Urlutil;

public class Dataaccess {
	public static final String baseurl = Urlstr.url;
	public static final String dbuser = "user";
	
	public static User getUserbyid(int id) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + dbuser + "/" + id;
		Map<String, Object> param = new HashMap<String, Object>();
		String userstr = Urlutil.util_get(urlStr, param);
		return new User(new JSONObject(userstr));
	}
	
	
	
	public static List<User> getUsers(Map<String, Object> param) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + dbuser + "/list";
		String userliststr = Urlutil.util_get(urlStr, param);
		JSONArray array = new JSONArray(userliststr);
		List<User> userlist = new ArrayList<User>();
		for(int i = 0; i < array.length(); i++)
		{
			JSONObject object = (JSONObject)array.get(i);
			userlist.add(new User(object));
		}
		return userlist;
	}
	
	public static String deleteUserbyid(int id) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + dbuser + "/" + id;
		Map<String, Object> param = new HashMap<String, Object>();
		String retstr = Urlutil.util_delete(urlStr,param);
		JSONObject jsonObj = new JSONObject(retstr);
		String ret = jsonObj.getString("status");
		return ret;
	}
	
	public static int createUser(Map<String, Object> param) throws JSONException, IOException
	{
		String urlStr = baseurl + "/" + dbuser;
		String retstr = Urlutil.util_post(urlStr, param);
		JSONObject jsonObj = new JSONObject(retstr);
		int ret = jsonObj.getInt("id");
		return ret;		
	}
	
	public static int updateUser(int id, Map<String, Object> param) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + dbuser + "/" + id;
		String retstr = Urlutil.util_put(urlStr, param);
		JSONObject jsonObj = new JSONObject(retstr);
		int ret = jsonObj.getInt("id");
		return ret;			
	}
}
