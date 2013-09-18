package cn.edu.sjtu.se.dslab.haiercloud.deploy;

import java.util.HashMap;

public class ClusterArguments {
	String clusterName;
	String masterIP;
	String secondaryNameNodeIP;
	HashMap<String,String> hm;
		
	public ClusterArguments() {}
	
	public ClusterArguments(String clusterName, String masterIP,
			HashMap<String, String> hm) {
		this.clusterName = clusterName;
		this.masterIP = masterIP;
		this.hm = hm;
	}
	
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getMasterIP() {
		return masterIP;
	}
	public void setMasterIP(String masterIP) {
		this.masterIP = masterIP;
	}
	public String getSecondaryNameNodeIP() {
		return secondaryNameNodeIP;
	}
	public void setSecondaryNameNodeIP(String secondaryNameNodeIP) {
		this.secondaryNameNodeIP = secondaryNameNodeIP;
	}
	public HashMap<String, String> getHm() {
		return hm;
	}
	public void setHm(HashMap<String, String> hm) {
		this.hm = hm;
	}
	
}
