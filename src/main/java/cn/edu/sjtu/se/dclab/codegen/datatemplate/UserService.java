package cn.edu.sjtu.se.dclab.codegen.datatemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sjtu.se.dclab.codegen.urlutil.Urlstr;
import cn.edu.sjtu.se.dclab.codegen.urlutil.Urlutil;

public class UserService {
	public static final String baseurl = Urlstr.url;
	public static final String db = new String("User").toLowerCase();
	
	public static User getUserbyid(int id) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/" + id;
		Map<String, Object> param = new HashMap<String, Object>();
		String Userstr = Urlutil.util_get(urlStr, param);
		return new User(new JSONObject(Userstr));
	}	
	
	public static List<User> getUsers(Map<String, Object> param) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/list";
		String Userliststr = Urlutil.util_get(urlStr, param);
		JSONArray array = new JSONArray(Userliststr);
		List<User> Userlist = new ArrayList<User>();
		for(int i = 0; i < array.length(); i++)
		{
			JSONObject object = (JSONObject)array.get(i);
			Userlist.add(new User(object));
		}
		return Userlist;
	}
	
	public static String deleteUserbyid(int id) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/" + id;
		Map<String, Object> param = new HashMap<String, Object>();
		String retstr = Urlutil.util_delete(urlStr,param);
		JSONObject jsonObj = new JSONObject(retstr);
		String ret = jsonObj.getString("status");
		return ret;
	}
	
	public static int createUser(Map<String, Object> param) throws JSONException, IOException
	{
		String urlStr = baseurl + "/" + db;
		String retstr = Urlutil.util_post(urlStr, param);
		JSONObject jsonObj = new JSONObject(retstr);
		int ret = jsonObj.getInt("id");
		return ret;		
	}
	
	public static int updateUser(int id, Map<String, Object> param) throws IOException, JSONException
	{
		String urlStr = baseurl + "/" + db + "/" + id;
		String retstr = Urlutil.util_put(urlStr, param);
		JSONObject jsonObj = new JSONObject(retstr);
		int ret = jsonObj.getInt("id");
		return ret;			
	}
}
