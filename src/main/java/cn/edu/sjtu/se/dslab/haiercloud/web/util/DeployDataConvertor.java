package cn.edu.sjtu.se.dslab.haiercloud.web.util;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.ClusterMeta;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.NodeMeta;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterMetaService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.INodeMetaService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IVirtualMachineService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.INodeService;

@Component("convertor")
public class DeployDataConvertor {

	// Properties
	@Resource(name = "clusterMetaService")
	IClusterMetaService cmService;

	@Resource(name = "nodeMetaService")
	INodeMetaService nmService;

	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;

	@Resource(name = "nodeService")
	INodeService nodeService;

	// Constructor
	public DeployDataConvertor() {

	}

	public Cluster convertHadoopDataToCluster(long namenode, long[] snnList,
			long[] dnList, long jobtracker, long[] ttList, String clusterName) {
		/*
		 * pre-database processing. save cluster in database, in order to show
		 * deploying status
		 */
		Cluster cluster = new Cluster();
		// clustermeta
		ClusterMeta meta = cmService.getClusterMetaByName("Hadoop");
		cluster.setMeta(meta);
		// name
		cluster.setName(clusterName);
		// status
		cluster.setStatus(VirtualMachine.DEPLOY); // 0:error, 1:success, 2:deploy
		// vms
		// get all selected virtual machine ids
		Set<Long> vms = new HashSet<Long>();
		vms.add(namenode);
		vms.add(jobtracker);
		for (long x : snnList) {
			vms.add(x);
		}
		for (long x : dnList) {
			vms.add(x);
		}
		// get vms from database
		Set<VirtualMachine> vmSet = new HashSet<VirtualMachine>();
		NodeMeta nnMeta = nmService.getNodeMetaByName("NameNode");
		NodeMeta dnMeta = nmService.getNodeMetaByName("DataNode");
		NodeMeta jtMeta = nmService.getNodeMetaByName("JobTracker");
		NodeMeta snnMeta = nmService.getNodeMetaByName("SecondaryNameNode");

		for (long id : vms) {
			VirtualMachine vm = vmService.getVirtualMachineById(id);
			Set<Node> nodes = new HashSet<Node>();
			// add corresponding nodes
			if (id == namenode) {
				Node nn = new Node(clusterName + "_NameNode", 9000, nnMeta, vm);
				nodes.add(nn);
				nodeService.addNode(nn);
			}

			if (id == jobtracker) {
				Node jt = new Node(clusterName + "_JobTracker", 9001, jtMeta,
						vm);
				nodes.add(jt);
				nodeService.addNode(jt);
			}

			for (long snnId : snnList) {
				if (snnId == id) {
					Node snn = new Node(clusterName + "_SecondaryNameNode",
							50090, snnMeta, vm);
					nodes.add(snn);
					nodeService.addNode(snn);
				}
			}

			for (long dnId : dnList) {
				if (dnId == id) {
					Node dn = new Node(clusterName + "_DataNode", 50010,
							dnMeta, vm);
					nodes.add(dn);
					nodeService.addNode(dn);
				}
			}

			vm.setNodes(nodes);
			vm.setCluster(cluster);
			vmSet.add(vm);
		}

		cluster.setVms(vmSet);
		return cluster;
	}

}
