package cn.edu.sjtu.se.dslab.haiercloud.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.edu.sjtu.se.dslab.haiercloud.deploy.ClusterArguments;
import cn.edu.sjtu.se.dslab.haiercloud.deploy.DeployHadoopCluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IVirtualMachineDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterMetaService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IDeployClusterService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.INodeMetaService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.INodeService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IVirtualMachineService;
import cn.edu.sjtu.se.dslab.haiercloud.web.util.DeployDataConvertor;

@Component("deployClusterService")
public class DeployClusterServiceImpl implements IDeployClusterService {

	// Properties
	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;

	@Resource(name = "deployHadoopCluster")
	DeployHadoopCluster dhc;

	@Resource(name = "clusterMetaService")
	IClusterMetaService cmService;

	@Resource(name = "nodeMetaService")
	INodeMetaService nmService;

	@Resource(name = "clusterService")
	IClusterService clusterService;

	@Resource(name = "nodeService")
	INodeService nodeService;

	@Resource(name = "convertor")
	DeployDataConvertor convertor;

	@Resource(name = "virtualMachineDao")
	IVirtualMachineDao vmDao;

	@Async
	public void deployHadoopCluster(long namenode, long[] snnList,
	        long[] dnList, long jobtracker, long[] ttList, String clusterName) {
		// process data
		Cluster cluster = convertor.convertHadoopDataToCluster(namenode,
		        snnList, dnList, jobtracker, ttList, clusterName);
		clusterService.addCluster(cluster);
		// end of pre-database processing

		// deploy
		VirtualMachine master = vmService.getVirtualMachineById(namenode);
		List<VirtualMachine> vmList = new ArrayList<VirtualMachine>();
		for (long id : dnList) {
			vmList.add(vmService.getVirtualMachineById(id));
		}

		HashMap<String, String> nodeMap = new HashMap<String, String>();
		for (VirtualMachine vm : vmList) {
			nodeMap.put(vm.getIp(), vm.getPassword());
		}
		nodeMap.put(master.getIp(), master.getPassword());

		ClusterArguments ca = new ClusterArguments(clusterName, master.getIp(),
		        nodeMap);
		boolean ret = dhc.addCluster(ca);

		// success or fail process
		if (ret) { // success
			cluster.setStatus(VirtualMachine.STABLE);
			for (VirtualMachine vm : cluster.getVms()) {
				vm.setStatus(VirtualMachine.STABLE);
				vmDao.update(vm);
			}
			clusterService.updateCluster(cluster);
		} else { // fail
			cluster.setStatus(VirtualMachine.ERROR);
			clusterService.updateCluster(cluster);
		}

		// update database
	}

	@Async
	public void addNodesToHadoopCluster(long clusterId, long[] vms) {
		// get all info
		List<VirtualMachine> vmList = new ArrayList<VirtualMachine>();
		for (long vmid : vms) {
			vmList.add(vmService.getVirtualMachineById(vmid));
		}
		Cluster cluster = clusterService.getClusterById(clusterId);

		// update database
		for (VirtualMachine vm : vmList) {
			vm.setCluster(cluster);
			vm.setStatus(VirtualMachine.DEPLOY);
			vmDao.update(vm);
		}
		// end of updating database

		// deploy
		ClusterArguments ca = new ClusterArguments();

		dhc.addNode(ca);
		// end of deploy

		// update database base on deploy status
		for (VirtualMachine vm : vmList) {
			vm.setStatus(VirtualMachine.STABLE);
			vmDao.update(vm);
		}
		// end of update databse base on deploy status
	}

	public void deployMongoDBCluster() {
		// TODO Auto-generated method stub
	}

	public void deployMySQLCluster() {
		// TODO Auto-generated method stub
	}
}
