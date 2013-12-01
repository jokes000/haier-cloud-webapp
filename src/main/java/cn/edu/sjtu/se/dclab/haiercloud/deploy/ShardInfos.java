package cn.edu.sjtu.se.dclab.haiercloud.deploy;

import java.util.Map;

public class ShardInfos {

	String shardName;
	Map<String, String> mongodMap;
	String arbiterIP;
	String arbiterPwd;
	
	public ShardInfos(String shardName, Map<String, String> mongodMap) {
		this.shardName = shardName;
		this.mongodMap = mongodMap;
	}

	public ShardInfos(String shardName, Map<String, String> mongodMap,
			String arbiterIP, String arbiterPwd) {
		this.shardName = shardName;
		this.mongodMap = mongodMap;
		this.arbiterIP = arbiterIP;
		this.arbiterPwd = arbiterPwd;
	}

	public String getShardName() {
		return shardName;
	}

	public void setShardName(String shardName) {
		this.shardName = shardName;
	}

	public Map<String, String> getMongodMap() {
		return mongodMap;
	}

	public void setMongodMap(Map<String, String> mongodMap) {
		this.mongodMap = mongodMap;
	}

	public String getArbiterIP() {
		return arbiterIP;
	}

	public void setArbiterIP(String arbiterIP) {
		this.arbiterIP = arbiterIP;
	}

	public String getArbiterPwd() {
		return arbiterPwd;
	}

	public void setArbiterPwd(String arbiterPwd) {
		this.arbiterPwd = arbiterPwd;
	}

}
