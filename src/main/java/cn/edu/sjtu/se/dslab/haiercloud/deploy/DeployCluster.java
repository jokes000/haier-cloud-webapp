package cn.edu.sjtu.se.dslab.haiercloud.deploy;

import java.util.HashMap;

public interface DeployCluster {

	public boolean addCluster(ClusterArguments clusterArgs);
	
	public void addNode(ClusterArguments clusterArgs);
	
	public void deleteNode(ClusterArguments clusterArgs);
	
	public void deleteCluster(ClusterArguments clusterArgs);
	
}
