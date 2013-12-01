package cn.edu.sjtu.se.dclab.haiercloud.deploy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.sjtu.se.dclab.haiercloud.deploy.DeployMongoDBCluster;
import cn.edu.sjtu.se.dclab.haiercloud.deploy.MongoDBArguments;
import cn.edu.sjtu.se.dclab.haiercloud.deploy.ShardInfos;

public class TestMongoDBDeploy {

	public static void main(String[] args) {
		/*String ip1="192.168.1.254";
		String pwd1="111111";
		String ip2="192.168.1.253";
		String pwd2="111111";
		String ip3="192.168.1.252";
		String pwd3="111111";
		String ip4="192.168.1.251";
		String pwd4="111111";
		Map<String,String> shardMap1=new HashMap<String,String>();
		shardMap1.put(ip1,pwd1);
		shardMap1.put(ip2,pwd2);
		Map<String,String> shardMap2=new HashMap<String,String>();
		shardMap2.put(ip3, pwd3);
		shardMap2.put(ip4, pwd4);
		
		Map<String,String> configServerMap=new HashMap<String,String>();
		configServerMap.put(ip1, pwd1);
		configServerMap.put(ip2,pwd2);
		configServerMap.put(ip3, pwd3);
		
		Map<String,String> mongosMap=new HashMap<String,String>();
		mongosMap.put(ip4, pwd4);
		
		ShardInfos shard1=new ShardInfos("xjtu",shardMap1,"","");
		ShardInfos shard2=new ShardInfos("sjtu",shardMap2,"","");
		List<ShardInfos> shardList=new ArrayList<ShardInfos>();
		shardList.add(shard1);
		shardList.add(shard2);
		
		MongoDBArguments ma=new MongoDBArguments();
		ma.setClusterName("myMongoDB");
		ma.setConfigServerMap(configServerMap);
		ma.setMongosMap(mongosMap);
		ma.setShardInfos(shardList);*/
		
		DeployMongoDBCluster deployer=new DeployMongoDBCluster();
		//deployer.addCluster(ma);
		
		
		/*MongoDBArguments ma2 = new MongoDBArguments();
		HashMap<String,String> mongosMap = new HashMap<String, String>();
		mongosMap.put("192.168.1.251", "111111");
		HashMap<String,String> mongodMap = new HashMap<String, String>();
		mongodMap.put("192.168.1.250", "111111");
		ShardInfos si = new ShardInfos("shard-3", mongodMap);
		List<ShardInfos> shardList = new ArrayList<ShardInfos>();
		shardList.add(si);
		
		ma2.setMongosMap(mongosMap);
		ma2.setShardInfos(shardList);*/
		
		//deployer.addShards(ma2);
		
		MongoDBArguments ma3 = new MongoDBArguments();
		HashMap<String,String> mongosMap3 = new HashMap<String,String>();
		mongosMap3.put("192.168.1.251","111111");
		ShardInfos si3 = new ShardInfos("shard-3",new HashMap<String,String>());
		List<ShardInfos> shardList3 = new ArrayList<ShardInfos>();
		shardList3.add(si3);
		ma3.setMongosMap(mongosMap3);
		ma3.setShardInfos(shardList3);
		deployer.deleteShards(ma3);
	}

}
