package cn.edu.sjtu.se.dclab.haiercloud.web.scale.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.sjtu.se.dclab.haiercloud.deploy.ClusterArguments;
import cn.edu.sjtu.se.dclab.haiercloud.deploy.DeployCluster;
import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IVirtualMachineDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.NodeMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dclab.haiercloud.web.scale.IHadoopScaling;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IClusterMetaService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IClusterService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.INodeMetaService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.INodeService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IVirtualMachineService;

@Component("hadoopScaling")
public class HadoopScalingImpl implements IHadoopScaling {
	
	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;

	@Resource(name = "deployHadoopCluster")
	DeployCluster dhc;

	@Resource(name = "clusterMetaService")
	IClusterMetaService cmService;

	@Resource(name = "nodeMetaService")
	INodeMetaService nmService;

	@Resource(name = "clusterService")
	IClusterService clusterService;

	@Resource(name = "nodeService")
	INodeService nodeService;

	@Resource(name = "virtualMachineDao")
	IVirtualMachineDao vmDao;
	
	public void expand(long clusterid) {
		Cluster cluster = clusterService.getClusterById(clusterid);
		
		List<VirtualMachine> vmList = vmService.queryUnusedVM();
		
		if (vmList.size() > 0) {
			VirtualMachine vm = vmList.get(0);
			
			NodeMeta dnMeta = nmService.getNodeMetaByName("datanode");
			Node node = new Node(cluster.getName() + "_datanode", 50010, dnMeta, vm);
			nodeService.addNode(node);
			
			Set<Node> nodeSet = new HashSet<Node>();
			nodeSet.add(node);
			vm.setNodes(nodeSet);
			vm.setCluster(cluster);
			vmService.updateVirtualMachine(vm);
			
			Set<VirtualMachine> tmp = cluster.getVms();
			tmp.add(vm);
			cluster.setVms(tmp);
			clusterService.updateCluster(cluster);
			
			ClusterArguments args = new ClusterArguments();
			args.setClusterName(cluster.getName());
			
			VirtualMachine master = null;
			boolean found = false;
			for (VirtualMachine virm : tmp) {
				for (Node n : virm.getNodes()) {
					if (n.getMeta().getName().equals("namenode")) {
						master = virm;
						found = true;
						break;
					}
				}
				if (found)
					break;
			}
			
			HashMap<String, String> nodeMap = new HashMap<String, String>();
			nodeMap.put(master.getIp(), master.getPassword());
			
			nodeMap.put(vm.getIp(), vm.getPassword());
			
			args.setMasterIP(master.getIp());
			args.setSecondaryNameNodeIP(master.getIp());
			args.setHm(nodeMap);
			
			dhc.addNode(args);
			
		} else {
			
		}
		
	}

	public void shrink(Cluster cluster) {
		
	}
}
