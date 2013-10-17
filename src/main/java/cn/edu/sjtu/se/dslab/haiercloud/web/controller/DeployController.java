/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-17 16:25
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.haiercloud.deploy.ClusterArguments;
import cn.edu.sjtu.se.dslab.haiercloud.deploy.DeployHadoopCluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IDeployClusterService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IVirtualMachineService;

@Controller
@RequestMapping(value = "/deploy")
public class DeployController {

	// Properties
	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;

	@Resource(name = "deployClusterService")
	IDeployClusterService dcService;

	@Resource(name = "clusterService")
	IClusterService clusterService;

	// Operations
	@RequestMapping(value = "/hadoop", method = RequestMethod.GET)
	public ModelAndView deployHadoop() {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/deploy/hadoop");

		// add data
		List<VirtualMachine> vmList = vmService.getVirtualMachineList();
		mav.addObject("vmList", vmList);

		return mav;
	}

	@RequestMapping(value = "/hadoop/submit", method = RequestMethod.POST)
	public ModelAndView submitHadoop(@RequestParam long namenode,
			@RequestParam long[] snnList, @RequestParam long[] dnList,
			@RequestParam long jobtracker, @RequestParam long[] ttList,
			@RequestParam String clusterName) {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/deploy/submit");

		// deploy
		dcService.deployHadoopCluster(namenode, snnList, dnList, jobtracker,
				ttList, clusterName);

		return mav;
	}

	@RequestMapping(value = "/hadoop/{id}/add", method = RequestMethod.POST)
	public void addVmsToCluster(@PathVariable(value = "id") long id,
			@RequestParam(value = "vms[]", required = true) long[] vms) {
		dcService.addNodesToHadoopCluster(id, vms);
	}

	@RequestMapping(value = "/mongodb", method = RequestMethod.GET)
	public ModelAndView deployMongoDB() {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/deploy/mongodb");

		// add data
		mav.addObject("vmList", vmService.getVirtualMachineList());

		return mav;
	}

	@RequestMapping(value = "/mongodb/submit", method = RequestMethod.POST)
	public ModelAndView submitMongoDB(@RequestParam long[] configserver,
			@RequestParam long[] mongos, @RequestParam long[] shard1,
			@RequestParam long[] shard2, @RequestParam String clusterName) {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/deploy/submit");
		
		// process
		dcService.deployMongoDBCluster(configserver, mongos, shard1, shard2, clusterName);

		return mav;
	}

	@RequestMapping(value = "/mysql", method = RequestMethod.GET)
	public ModelAndView deployMySQL() {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/deploy/mysql");

		// add data

		return mav;
	}
}
