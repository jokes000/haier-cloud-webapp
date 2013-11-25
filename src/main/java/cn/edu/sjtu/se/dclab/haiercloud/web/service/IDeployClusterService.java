/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-19 15:53
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.service;

public interface IDeployClusterService {

	public void deployHadoopCluster(long namenode, long[] snnList,
			long[] dnList, long jobtracker, long[] ttList, String clusterName);

	public void addNodesToHadoopCluster(long clusterId, String namenodeIP,long[] vms);
	
	public void deleteNodesInHadoopCluster(long clusterId,String namenodeIP,long[] vms);

	public void deployMongoDBCluster(long[] configserver, long[] mongos,
			long[] shard1, long[] shard2, String clusterName);

	public void deployMySQLCluster();
}
