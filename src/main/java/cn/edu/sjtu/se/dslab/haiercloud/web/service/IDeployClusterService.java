/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-19 15:53
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.service;

public interface IDeployClusterService {
	
	public void deployHadoopCluster(long namenode,
															 long[] snnList,
															 long[] dnList,
															 long jobtracker,
															 long[] ttList,
															 String clusterName);
	
	public void addNodesToHadoopCluster(long clusterId, long[] vms);
	
	public void deployMongoDBCluster();
	
	public void deployMySQLCluster();
}
