package cn.edu.sjtu.se.dslab.haiercloud.deploy;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import cn.edu.sjtu.se.dslab.haiercloud.deploy.ClusterArguments;

public class TestDeployHadoopCluster {

	@Test
	public void testAddNode() {
		String masterIP="192.168.1.250";
		String masterPassword="111111";
		String nodeIP="192.168.1.251";
		String nodePassword="111111";
		HashMap<String, String> hm=new HashMap<String,String>();
		
		hm.put(masterIP, masterPassword);
		hm.put(nodeIP, nodePassword);
		ClusterArguments argus=new ClusterArguments();
		argus.setClusterName("myHadoop");
		argus.setMasterIP(masterIP);
		argus.setHm(hm);
		
		hm.put(masterIP,masterPassword);
		hm.put(nodeIP, nodePassword);
		new DeployHadoopCluster().addCluster(argus);
	}

}
