package cn.edu.sjtu.se.dclab.haiercloud.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IVirtualMachineDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dclab.haiercloud.web.scale.IHadoopScaling;
import cn.edu.sjtu.se.dclab.haiercloud.web.scale.IMongodbScaling;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IClusterMetaService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IClusterService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.INodeMetaService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.INodeService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IVirtualMachineService;

@Controller("scaling")
public class ScalingController {
	
	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;

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
	
	@Resource(name = "hadoopScaling")
	IHadoopScaling hadoopScaling;
	
	//@Resource(name = "mongodbScaling")
	//IMongodbScaling mongodbScaling;
	
	@RequestMapping(value = "/scaling/add")
	public void expand(@RequestParam String vmname, @RequestParam String type) {
		// get cluster info by vmid
		VirtualMachine vm = vmService.getVirtualMachineByName(vmname);
		Cluster cluster = vm.getCluster();
		
		if (cluster != null) {
			// verify cluster info 
			if (cluster.getMeta().getName().equals("hadoop")) {
				hadoopScaling.expand(cluster.getId());
			} else if (cluster.getMeta().getName().equals("mongodb")) {
				if (type.equals("cpu") || type.equals("memory")) {
					//mongodbScaling.expandMongos(vmname);
				} else {
					//mongodbScaling.expandShards(vmname);
				}
			} else {
				
			}
		}
	}
	
	@RequestMapping(value = "/scaling/delete")
	public void shrink(@RequestParam String vmname) {
		
	}
}
