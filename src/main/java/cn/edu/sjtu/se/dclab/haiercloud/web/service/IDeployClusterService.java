/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-19 15:53
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.service;

import javax.servlet.http.HttpServletRequest;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface IDeployClusterService {

	public void deployHadoopCluster(long namenode, long[] snnList,
			long[] dnList, long jobtracker, long[] ttList, String clusterName);

	public void addNodesToHadoopCluster(long clusterId, String namenodeIP,
			long[] vms);

	/*
	 * 袁长意
	 */
	public void deleteNodesInHadoopCluster(long clusterId, String namenodeIP,
			long nodeId, String nodeIP);

	public void deployMongoDBCluster(long[] configserver, long[] mongos,
			long[] shard1, long[] shard2, String clusterName, User user);

	public void addShardToMongoDBCluster(long clusterId, long[] vms,
			long[] mongosIds, int shardNum);

	public void deleteShardInMongoDBCluster(long clusterId, long[] mongosIds,
			String shardName);

	public void deployMySQLCluster();
}
