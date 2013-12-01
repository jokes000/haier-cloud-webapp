package cn.edu.sjtu.se.dclab.haiercloud.deploy;

import java.util.List;
import java.util.Map;

import cn.edu.sjtu.se.dclab.haiercloud.deploy.ShardInfos;

public class MongoDBArguments {

	String clusterName = null;
	List<ShardInfos> shardList = null;
	Map<String, String> configServerMap = null;
	Map<String, String> mongosMap = null;

	public MongoDBArguments() {
	}

	public MongoDBArguments(String clusterName, List<ShardInfos> shardList,
			Map<String, String> configServerMap, Map<String, String> mongosMap) {
		this.clusterName = clusterName;
		this.shardList = shardList;
		this.configServerMap = configServerMap;
		this.mongosMap = mongosMap;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public List<ShardInfos> getShardList() {
		return shardList;
	}

	public void setShardInfos(List<ShardInfos> shardList) {
		this.shardList = shardList;
	}

	public Map<String, String> getConfigServerMap() {
		return configServerMap;
	}

	public void setConfigServerMap(Map<String, String> configServerMap) {
		this.configServerMap = configServerMap;
	}

	public Map<String, String> getMongosMap() {
		return mongosMap;
	}

	public void setMongosMap(Map<String, String> mongosMap) {
		this.mongosMap = mongosMap;
	}

}
