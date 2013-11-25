package cn.edu.sjtu.se.dclab.haiercloud.deploy;

import cn.edu.sjtu.se.dclab.haiercloud.deploy.ClusterArguments;

public interface DeployCluster {

	public boolean addCluster(ClusterArguments clusterArgs);
	
	public void addNode(ClusterArguments clusterArgs);
	
	public void deleteNode(ClusterArguments clusterArgs);
	
	public void deleteCluster(ClusterArguments clusterArgs);
	
}
