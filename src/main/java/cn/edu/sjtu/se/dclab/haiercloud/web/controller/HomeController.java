/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-16 16:19
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.controller;

// Spring support
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.GangliaPropertyProvider;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.StreamProvider;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.URLStreamProvider;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.VMStatus;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IAuthService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IClusterMetaService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IClusterService;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IVirtualMachineService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@Resource(name = "clusterMetaService")
	IClusterMetaService cmService;

	@Resource(name = "clusterService")
	IClusterService cService;

	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;

	@Resource(name = "authService")
	IAuthService authService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectLogin() {

		if (!authService.isAuthenticated()) {
			return "redirect:/login";
		}

		return "redirect:/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHomePage() {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/home");

		return mav;
	}

	@RequestMapping(value = "/cluster", method = RequestMethod.GET)
	public ModelAndView getClusterList(
			@RequestParam(value = "page", defaultValue = "1") int page) {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/cluster");

		// add data
		int pageSize = 3;

		long totalNumber = cService.totalNumber();
		mav.addObject("total", totalNumber);

		System.out.println(totalNumber);

		List<Cluster> list = cService.getByPage(page, pageSize);
		mav.addObject("clusterList", list);

		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		return mav;
	}

	@RequestMapping(value = "/deploy", method = RequestMethod.GET)
	public ModelAndView deployCluster() {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/deploy");

		return mav;
	}

	@RequestMapping(value = "/vm", method = RequestMethod.GET)
	public ModelAndView vmManagement(
			@RequestParam(value = "page", defaultValue = "1") int page) throws Exception {

		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/vm");

		// get data
		// add data
		int pageSize = 10;

		long totalNumber = vmService.totalNumber();
		mav.addObject("total", totalNumber);

		StreamProvider metricProvider = new URLStreamProvider(1500, 10000,
				"ssl.trustStore.path", "ssl.trustStore.password",
				"ssl.trustStore.type");
		GangliaPropertyProvider gp = new GangliaPropertyProvider(metricProvider);
		List<VirtualMachine> list = vmService.getByPage(page, pageSize);
		List<VMStatus> statusList = new ArrayList<VMStatus>();
		for (VirtualMachine vm : list) {
			System.out.println("Server name is:" + vm.getName());
			VMStatus status = gp.getMetrics(vm.getName() + ".local");
			
			status.setName(vm.getName());
			status.setIp(vm.getIp());
			status.setCpu(vm.getCpu());
			status.setMemory(vm.getMemory() + "");
			status.setStorage(vm.getStorage() + "");
			status.setBoardWidth(vm.getBoardWidth() + "");
			statusList.add(status);
		}
		mav.addObject("vmList", statusList);

		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		return mav;
	}
}