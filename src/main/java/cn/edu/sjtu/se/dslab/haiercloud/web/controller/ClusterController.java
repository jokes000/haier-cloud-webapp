package cn.edu.sjtu.se.dslab.haiercloud.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterService;

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
			Set<VirtualMachine> dn = new HashSet<VirtualMachine>();

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
					if (meta.equals("datanode") || meta.equals("tasktracker")) {
						dn.add(vm);
					}
				}
			}
			mav.addObject("nn", nn);
			mav.addObject("snn", snn);
			mav.addObject("jt", jt);
			mav.addObject("dn", dn);
		} else if (cluster.getMeta().getName().equals("mongodb")) {
			List<VirtualMachine> configserver = new ArrayList<VirtualMachine>(); 
			List<VirtualMachine> mongos = new ArrayList<VirtualMachine>();
			
		} else if (cluster.getMeta().getName().equals("mysql")) {
			
		}
		
		// configure redirect
		mav.setViewName("/cluster/modify/"
				+ cluster.getMeta().getName().toLowerCase());

		return mav;
	}
}
