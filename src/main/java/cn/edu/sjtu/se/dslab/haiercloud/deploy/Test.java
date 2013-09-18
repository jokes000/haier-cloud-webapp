package cn.edu.sjtu.se.dslab.haiercloud.deploy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Test {
	
	public static void main(String[] args) {
		
		String masterIP="192.168.1.114";
		String masterPassword="111111";
		String nodeIP="192.168.1.184";
		String nodePassword="111111";
		String node2IP="192.168.1.185";
		String node2Password="111111";
		HashMap<String, String> hm=new HashMap<String,String>();
		
		hm.put(masterIP, masterPassword);
		hm.put(nodeIP, nodePassword);
		ClusterArguments argus=new ClusterArguments();
		argus.setClusterName("sjtu");
		argus.setMasterIP(masterIP);
		argus.setHm(hm);
		//new DeployHadoopCluster().addCluster(argus);
		
		new DeployHadoopCluster().deleteCluster(argus);
		/*hm.put(masterIP, masterPassword);
		hm.put(nodeIP, nodePassword);
		hm.put(node2IP, node2Password);
		new DeployHadoopCluster().deleteNode("sjtu",masterIP, hm);*/
		
		/*hm.put(masterIP, masterPassword);
		hm.put(nodeIP, nodePassword);
		//hm.put(node2IP, node2Password);
		new DeployHadoopCluster().addNode("sjtu",masterIP,hm);*/
		
		/*hm.put(masterIP,masterPassword);
		hm.put(nodeIP, nodePassword);
		hm.put(node2IP, node2Password);
		new DeployHadoopCluster().deleteCluster("sjtu",masterIP, hm);*/
	}

}
