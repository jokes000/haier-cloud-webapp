package cn.edu.sjtu.se.dclab.haiercloud.web.scale;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Cluster;

public interface IHadoopScaling {
	
	public void expand(long clusterid);
	
	public void shrink(Cluster cluster);
}
