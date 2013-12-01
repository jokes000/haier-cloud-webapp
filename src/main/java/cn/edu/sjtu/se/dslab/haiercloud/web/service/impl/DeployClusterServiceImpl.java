package cn.edu.sjtu.se.dslab.haiercloud.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.haiercloud.deploy.ClusterArguments;
import cn.edu.sjtu.se.dslab.haiercloud.deploy.DeployHadoopCluster;
import cn.edu.sjtu.se.dslab.haiercloud.deploy.DeployMongoDBCluster;
import cn.edu.sjtu.se.dslab.haiercloud.deploy.MongoDBArguments;
import cn.edu.sjtu.se.dslab.haiercloud.deploy.ShardInfos;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IVirtualMachineDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.ClusterMeta;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.NodeMeta;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterMetaService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IDeployClusterService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.INodeMetaService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.INodeService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IVirtualMachineService;

@Component("deployClusterService")
@Transactional
public class DeployClusterServiceImpl implements IDeployClusterService {

	// Properties
	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;

	@Resource(name = "deployHadoopCluster")
	DeployHadoopCluster dhc;

	@Resource(name = "deployMongodbCluster")
	DeployMongoDBCluster dmc;

	@Resource(name = "clusterMetaService")
	IClusterMetaService cmService;

	@Resource(name = "nodeMetaService")
	INodeMetaService nmService;

	@Resource(name = "clusterService")
	IClusterService clusterService;

	@Resource(name = "nodeService")
	INodeService nodeService;

	// @Resource(name = "convertor")
	// DeployDataConvertor convertor;

	@Resource(name = "virtualMachineDao")
	IVirtualMachineDao vmDao;

	@Async
	public void deployHadoopCluster(long namenode, long[] snnList,
			long[] dnList, long jobtracker, long[] ttList, String clusterName) {
		// process data
		// Cluster cluster = convertor.convertHadoopDataToCluster(namenode,
		// snnList, dnList, jobtracker, ttList, clusterName);

		Cluster cluster = new Cluster();
		// cluster meta
		ClusterMeta meta = cmService.getClusterMetaByName("hadoop");
		cluster.setMeta(meta);
		// name
		cluster.setName(clusterName);
		// status
		cluster.setStatus(VirtualMachine.DEPLOY);
		// vms
		// get all selected virtual machine ids
		Set<Long> vms = new HashSet<Long>();
		vms.add(namenode);
		vms.add(jobtracker);
		for (long id : snnList) {
			vms.add(id);
		}
		for (long id : dnList) {
			vms.add(id);
		}

		// get vms from database
		Set<VirtualMachine> vmSet = new HashSet<VirtualMachine>();
		NodeMeta nnMeta = nmService.getNodeMetaByName("namenode");
		NodeMeta dnMeta = nmService.getNodeMetaByName("datanode");
		NodeMeta jtMeta = nmService.getNodeMetaByName("jobtracker");
		NodeMeta snnMeta = nmService.getNodeMetaByName("secondarynamenode");

		for (long id : vms) {
			VirtualMachine vm = vmService.getVirtualMachineById(id);
			Set<Node> nodes = new HashSet<Node>();

			// add corresponding nodes
			if (namenode == id) {
				Node nn = new Node(clusterName + "_namenode", 9000, nnMeta, vm);
				nodes.add(nn);
				nodeService.addNode(nn);
			}

			if (jobtracker == id) {
				Node jt = new Node(clusterName + "_jobtracker", 9001, jtMeta,
						vm);
				nodes.add(jt);
				nodeService.addNode(jt);
			}

			for (long snnId : snnList) {
				if (snnId == id) {
					Node snn = new Node(clusterName + "_secondarynamenode",
							50090, snnMeta, vm);
					nodes.add(snn);
					nodeService.addNode(snn);
				}
			}

			for (long dnId : dnList) {
				if (dnId == id) {
					Node dn = new Node(clusterName + "_datanode", 50010,
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
		clusterService.addCluster(cluster);
		// end of pre-database processing

		// master vm
		VirtualMachine master = vmService.getVirtualMachineById(namenode);

		HashMap<String, String> nodeMap = new HashMap<String, String>();

		for (VirtualMachine vm : vmSet) {
			nodeMap.put(vm.getIp(), vm.getPassword());
		}
		nodeMap.put(master.getIp(), master.getPassword());

		ClusterArguments ca = new ClusterArguments();
		ca.setClusterName(clusterName);
		ca.setMasterIP(master.getIp());
		ca.setSecondaryNameNodeIP(master.getIp());
		ca.setHm(nodeMap);

		/*
		 * Properties pt = new Properties();
		 * 
		 * InputStream in = getClass().getResourceAsStream(
		 * "/WEB-INF/classes/hadoop.properties"); try { pt.load(in); } catch
		 * (IOException ioe) { ioe.printStackTrace(); }
		 */

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
	public void addNodesToHadoopCluster(long clusterId,String namenodeIP,long[] vms) {
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
		String namenodePassword="";
		Set<VirtualMachine> set=cluster.getVms();
		Iterator<VirtualMachine> iterator=set.iterator();
		while(iterator.hasNext()){
			VirtualMachine vm=iterator.next();
			if(vm.getIp().equals(namenodeIP))
				namenodePassword=vm.getPassword();
		}
		HashMap<String,String> hm=new HashMap<String, String>();
		hm.put(namenodeIP,namenodePassword);
		for(VirtualMachine vm : vmList){
			hm.put(vm.getIp(), vm.getPassword());
		}
		// deploy
		ClusterArguments ca = new ClusterArguments();
		ca.setClusterName(cluster.getName());
		ca.setMasterIP(namenodeIP);
		ca.setHm(hm);
		/*
		 * Properties pt = new Properties(); InputStream in =
		 * getClass().getResourceAsStream("/WEB-INF/hadoop.properties"); try {
		 * pt.load(in); } catch (IOException ioe) { ioe.printStackTrace(); }
		 */

		dhc.addNode(ca);
		// end of deploy

		// update database base on deploy status
		
		NodeMeta dataNodeMeta = nmService.getNodeMetaByName("datanode");
		
		for (VirtualMachine vm : vmList) {
			Node datanode = new Node();
			datanode.setName(cluster.getName() + "_" + "datanode");
			datanode.setPort(50010);
			datanode.setVm(vm);
			datanode.setMeta(dataNodeMeta);
			
			vm.setStatus(VirtualMachine.STABLE);
			vm.getNodes().add(datanode);
			vmDao.update(vm);
			nodeService.addNode(datanode);
		}
		// end of update databse base on deploy status
	}
	
	/*
	 * 袁长意
	 * 
	 */
	@Async
	public void deleteNodesInHadoopCluster(long clusterId,String namenodeIP,long nodeId,String nodeIP) {
		// get all info
		Cluster cluster = clusterService.getClusterById(clusterId);
		
		// end of updating database
		String namenodePassword="";
		String nodePassword="";
		Set<VirtualMachine> vms=cluster.getVms();
		Iterator<VirtualMachine> iterator=vms.iterator();
		while(iterator.hasNext()){
			VirtualMachine vm=iterator.next();
			if(vm.getIp().equals(namenodeIP))
				namenodePassword=vm.getPassword();
			else if(vm.getIp().equals(nodeIP))
				nodePassword=vm.getPassword();
		}
		HashMap<String,String> hm=new HashMap<String, String>();
		hm.put(namenodeIP,namenodePassword);
		hm.put(nodeIP, nodePassword);
		// deploy
		ClusterArguments ca = new ClusterArguments();
		ca.setClusterName(cluster.getName());
		ca.setMasterIP(namenodeIP);
		ca.setHm(hm);
		
		 /* Properties pt = new Properties(); InputStream in =
		 * getClass().getResourceAsStream("/WEB-INF/hadoop.properties"); try {
		 * pt.load(in); } catch (IOException ioe) { ioe.printStackTrace(); }
		 */

		dhc.deleteNode(ca);
		// end of deploy

		// update database
		Node node = nodeService.getNodeById(nodeId);
		VirtualMachine nodeVM=node.getVm();
		nodeVM.setCluster(null);
		vmService.updateVirtualMachine(nodeVM);
		nodeService.deleteNode(node);
		// end of update databse base on deploy status
	}
	

	@Async
	public void deployMongoDBCluster(long[] configserver, long[] mongos,
			long[] shard1, long[] shard2, String clusterName) {

		// pre-database process
		Cluster cluster = new Cluster();
		// cluster meta
		ClusterMeta meta = cmService.getClusterMetaByName("mongodb");
		cluster.setMeta(meta);
		// name
		cluster.setName(clusterName);
		// status
		cluster.setStatus(VirtualMachine.DEPLOY);
		// vms
		// get all selected virtual machine ids
		Set<Long> vms = new HashSet<Long>();
		for (long id : configserver) 
			vms.add(id);
		for (long id : mongos) 
			vms.add(id);
		for (long id : shard1) 
			vms.add(id);
		for (long id : shard2) 
			vms.add(id);
		
		Set<VirtualMachine> vmSet = new HashSet<VirtualMachine>();
		NodeMeta csMeta = nmService.getNodeMetaByName("configserver");
		NodeMeta msMeta = nmService.getNodeMetaByName("mongos");
		NodeMeta mdMeta = nmService.getNodeMetaByName("mongod");
		
		String parent1=clusterName+"_shard1";
		String parent2=clusterName+"_shard2";
		
		for (long id : vms) {
			VirtualMachine vm = vmService.getVirtualMachineById(id);
			Set<Node> nodes = new HashSet<Node>();

			// add corresponding nodes
			for (long csid : configserver) {
				if (csid == id) {
					Node cs = new Node(clusterName + "_configserver", 20000, csMeta, vm);
					nodes.add(cs);
					nodeService.addNode(cs);
				}
			}

			for (long msid : mongos) {
				if (msid == id) {
					Node ms = new Node(clusterName + "_mongos", 30100, msMeta, vm);
					nodes.add(ms);
					nodeService.addNode(ms);
				}
			}
			
//			int count = 0;
//			Node parent = null;
			for (long mongod : shard1) {
				if (mongod == id) {
					Node md = new Node(clusterName + "_mongod", 30000, mdMeta, vm);
//					if (count++ == 0) {
//						md.setParent(md);
//						parent = md;
//					} else {
//						md.setParent(parent);
//					}
					md.setParent(parent1);
					nodes.add(md);
					nodeService.addNode(md);
				}
			}
			
//			count = 0;
			for (long mongod : shard2) {
				if (mongod == id) {
					Node md = new Node(clusterName + "_mongod", 27017, mdMeta, vm);
//					if (count++ == 0) {
//						md.setParent(md);
//						parent = md;
//					} else {
//						md.setParent(parent);
//					}
					md.setParent(parent2);
					nodes.add(md);
					nodeService.addNode(md);
				}
			}
			
			vm.setNodes(nodes);
			vm.setCluster(cluster);
			vmSet.add(vm);
		}
		
		cluster.setVms(vmSet);
		clusterService.addCluster(cluster);

		// init parameters
		Map<String, String> shardMap1 = new HashMap<String, String>();
		for (long id : shard1) {
			VirtualMachine vm = vmService.getVirtualMachineById(id);
			shardMap1.put(vm.getIp(), vm.getPassword());
		}
		ShardInfos si1 = new ShardInfos(clusterName+"_shard-1", shardMap1);

		Map<String, String> shardMap2 = new HashMap<String, String>();
		for (long id : shard2) {
			VirtualMachine vm = vmService.getVirtualMachineById(id);
			shardMap2.put(vm.getIp(), vm.getPassword());
		}
		ShardInfos si2 = new ShardInfos(clusterName+"_shard-2", shardMap2);

		List<ShardInfos> shardList = new ArrayList<ShardInfos>();
		shardList.add(si1);
		shardList.add(si2);

		Map<String, String> configServerMap = new HashMap<String, String>();
		for (long id : configserver) {
			VirtualMachine vm = vmService.getVirtualMachineById(id);
			configServerMap.put(vm.getIp(), vm.getPassword());
		}

		Map<String, String> mongosMap = new HashMap<String, String>();
		for (long id : mongos) {
			VirtualMachine vm = vmService.getVirtualMachineById(id);
			mongosMap.put(vm.getIp(), vm.getPassword());
		}

		MongoDBArguments ma = new MongoDBArguments(clusterName, shardList,
				configServerMap, mongosMap);

		// deploy
		boolean ret = dmc.addCluster(ma);

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
	}

	public void addShardToMongoDBCluster(long clusterId,long[] vms,long[] mongosIds,int shardNum){
		Map<String,String> mongosMap = new HashMap<String,String>();
		Map<String,String> mongodMap = new HashMap<String,String>();
		List<ShardInfos> shardList = new ArrayList<ShardInfos>();
		Cluster cluster = clusterService.getClusterById(clusterId);
		String shardName = cluster.getName()+"_shard"+(shardNum+1);
		
		for(int i=0;i<mongosIds.length;i++){
			VirtualMachine vm = vmService.getVirtualMachineById(mongosIds[i]);
			mongosMap.put(vm.getIp(),vm.getPassword());
		}
		
		//update vm table
		for(int i=0;i<vms.length;i++){
			VirtualMachine vm = vmService.getVirtualMachineById(vms[i]);
			mongodMap.put(vm.getIp(), vm.getPassword());
			vm.setCluster(cluster);
			vm.setStatus(VirtualMachine.DEPLOY);
			vmDao.update(vm);
		}
		ShardInfos shardInfos = new ShardInfos(shardName, mongodMap);
		shardList.add(shardInfos);
		
		MongoDBArguments ma = new MongoDBArguments();
		ma.setMongosMap(mongosMap);
		ma.setShardInfos(shardList);
		ma.setClusterName(cluster.getName());
		
		boolean ret = dmc.addShards(ma);
		
		// update database base on deploy status
		if(ret == true){

			NodeMeta dataNodeMeta = nmService.getNodeMetaByName("mongod");

			for (int i = 0; i < vms.length; i++) {
				VirtualMachine vm = vmService.getVirtualMachineById(vms[i]);
				Node datanode = new Node();
				datanode.setName(cluster.getName() + "_" + "mongod");
				datanode.setPort(30000);
				datanode.setVm(vm);
				datanode.setParent(shardName);
				datanode.setMeta(dataNodeMeta);

				vm.setStatus(VirtualMachine.STABLE);
				vm.getNodes().add(datanode);
				vmDao.update(vm);
				nodeService.addNode(datanode);
			}
		}
		
	}
	
	
	public void deleteShardInMongoDBCluster(long clusterId,long[] mongosIds,String shardName){
		
		//先处理数据
		Map<String,String> mongodsMap = new HashMap<String,String>();
		Cluster cluster = clusterService.getClusterById(clusterId);
		Iterator<VirtualMachine> vmsIterator = cluster.getVms().iterator();
		while(vmsIterator.hasNext()){
			VirtualMachine vm = vmsIterator.next();
			Iterator<Node> nodesIterator = vm.getNodes().iterator();
			while(nodesIterator.hasNext()){
				Node node = nodesIterator.next();
				if(node.getParent()!=null && node.getParent().equals(shardName))
					mongodsMap.put(vm.getIp(),vm.getPassword());
			}
		}
		ShardInfos shardInfos = new ShardInfos(shardName, mongodsMap);
		List<ShardInfos> shardList = new ArrayList<ShardInfos>();
		shardList.add(shardInfos);
		HashMap<String,String> mongosMap = new HashMap<String, String>();
		for(int i=0;i<mongosIds.length;i++){
			VirtualMachine vm = vmService.getVirtualMachineById(mongosIds[i]);
			mongosMap.put(vm.getIp(), vm.getPassword());
		}
		
		MongoDBArguments ma = new MongoDBArguments();
		ma.setMongosMap(mongosMap);
		ma.setShardInfos(shardList);
		ma.setClusterName(cluster.getName());
		
		//调用底层的删除操作
		dmc.deleteShards(ma);
		
		//更新数据库，一个是vm,一个是node
		Set<VirtualMachine> vms = clusterService.getClusterById(clusterId).getVms();
		vmsIterator = vms.iterator();
		while(vmsIterator.hasNext()){
			VirtualMachine vm = vmsIterator.next();
			Set<Node> nodes = vm.getNodes();
			Iterator<Node> nodesIterator = nodes.iterator();
			while(nodesIterator.hasNext()){
				Node node = nodesIterator.next();
				if((node.getParent() != null) && node.getParent().equals(shardName)){
					vm.getNodes().remove(node);
					nodeService.deleteNode(node);
				}
			}
			if(vm.getNodes().isEmpty()){
				vm.setCluster(null);
				vmService.updateVirtualMachine(vm);
			}
		}
	}
	
	public void deployMySQLCluster() {
		// TODO Auto-generated method stub
	}
}
