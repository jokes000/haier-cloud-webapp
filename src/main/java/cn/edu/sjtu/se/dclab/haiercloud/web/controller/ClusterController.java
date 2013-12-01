package cn.edu.sjtu.se.dclab.haiercloud.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.metamodel.SetAttribute;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IClusterService;

@Controller
@RequestMapping(value = "/cluster")
public class ClusterController {

	// Properties
	@Resource(name = "clusterService")
	IClusterService clusterService;

	// Request Handlers
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public ModelAndView modifyCluster(@PathVariable(value = "id") long id) {
		ModelAndView mav = new ModelAndView();

		// add data
		Cluster cluster = clusterService.getClusterById(id);
		mav.addObject("cluster", cluster);

		if (cluster.getMeta().getName().equals("hadoop")) {
			VirtualMachine nn = null;
			Set<VirtualMachine> snn = new HashSet<VirtualMachine>();
			VirtualMachine jt = null;
			List<VirtualMachine> dn = new ArrayList<VirtualMachine>();
			List<Integer> dnIds=new ArrayList<Integer>();

			for (VirtualMachine vm : cluster.getVms()) {
				
				for (Node node : vm.getNodes()) {
					String meta = node.getMeta().getName();
					if (meta.equals("namenode")) {
						nn = vm;
					}
					if (meta.equals("secondarynamenode")) {
						snn.add(vm);
					}
					if (meta.equals("jobtracker")) {
						jt = vm;
					}
					if (meta.equals("datanode")||meta.equals("tasktracker")) {
						dn.add(vm);
						dnIds.add((int)node.getId());
					}
				}
			}
			mav.addObject("nn", nn);
			mav.addObject("snn", snn);
			mav.addObject("jt", jt);
			mav.addObject("dn", dn);
			mav.addObject("dnIds",dnIds);
		} else if (cluster.getMeta().getName().equals("mongodb")) {
			List<VirtualMachine> configserver = new ArrayList<VirtualMachine>(); 
			List<VirtualMachine> mongos = new ArrayList<VirtualMachine>();
			Map<String,ArrayList<VirtualMachine>> shards=new HashMap<String,ArrayList<VirtualMachine>>();
			
			for (VirtualMachine vm : cluster.getVms()) {
				for(Node node : vm.getNodes()){
					String meta=node.getMeta().getName();
					if(meta.equals("configserver"))
						configserver.add(vm);
					if(meta.equals("mongos"))
						mongos.add(vm);
					if(meta.equals("mongod")){
						ArrayList<VirtualMachine> hs=shards.get(node.getParent());
						if(hs!=null)
							hs.add(vm);
						else{
							hs=new ArrayList<VirtualMachine>();
							hs.add(vm);
						}
						shards.put(node.getParent(),hs);
					}
				}
			}
			
			List<Long> mongosIds=new ArrayList<Long>();
			for(int i=0;i<mongos.size();i++)
				mongosIds.add(mongos.get(i).getId());			
			
			mav.addObject("configserver",configserver);
			mav.addObject("mongos",mongos);
			mav.addObject("shards",shards.entrySet());
			mav.addObject("mongosIds",mongosIds);
			
		} else if (cluster.getMeta().getName().equals("mysql")) {
			
			
			
		}
		
		// configure redirect
		mav.setViewName("/cluster/modify/"
				+ cluster.getMeta().getName().toLowerCase());

		return mav;
	}
}
