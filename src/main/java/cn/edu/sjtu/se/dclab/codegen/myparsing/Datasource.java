package cn.edu.sjtu.se.dclab.codegen.myparsing;

import java.util.Vector;

public class Datasource {
	
	private String name;
	private String type;
	private String host;
	private String port;
	private String url;
	private String index;
	private String databaseName;
	private String user;
	private String password;
	
	private Vector<Bean> vbs = new Vector<Bean>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Vector<Bean> getVbs() {
		return vbs;
	}
	public void setVbs(Vector<Bean> vbs) {
		this.vbs = vbs;
	}
	
	
	
}
