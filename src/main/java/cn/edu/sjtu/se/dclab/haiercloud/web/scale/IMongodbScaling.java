package cn.edu.sjtu.se.dclab.haiercloud.web.scale;

public interface IMongodbScaling {

	public void expandMongos(String vmname);
	
	public void shrinkMongos(String vmname);
	
	public void expandShards(String vmname);
	
	public void shrinkShards(String vmname);
	
	public void expandReplicaSet(String vmname);
	
	public void shrinkReplicaSet(String vmname);
}
