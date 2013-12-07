package cn.edu.sjtu.se.dclab.haiercloud.web.scale.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.edu.sjtu.se.dclab.haiercloud.deploy.ClusterArguments;
import cn.edu.sjtu.se.dclab.haiercloud.deploy.DeployHadoopCluster;
import cn.edu.sjtu.se.dclab.haiercloud.web.scale.ICPUScaling;
import cn.edu.sjtu.se.dclab.haiercloud.web.scale.IDiskScaling;
import cn.edu.sjtu.se.dclab.haiercloud.web.scale.IMemoryScaling;

@Component("hadoopScaling")
public class HadoopScalingImpl implements ICPUScaling, IDiskScaling, IMemoryScaling {
	
	@Resource(name = "deployHadoopCluster")
	DeployHadoopCluster dhc;
	
	@Async
	public void TestAddNode(ClusterArguments args) {
		dhc.addNode(args);
	}
}
