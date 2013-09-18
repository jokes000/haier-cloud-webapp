package cn.edu.sjtu.se.dslab.haiercloud.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IVirtualMachineService;

@Controller
@RequestMapping(value = "/vm")
public class VMController {

	// Properties
	@Resource(name = "virtualMachineService")
	IVirtualMachineService vmService;
	
	// Operations
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView vmAdd() {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/vm/add");

		return mav;
	}

	@RequestMapping(value = "/add/submit", method = RequestMethod.POST)
	public ModelAndView vmSubmit(VirtualMachine vm) {
		// configure redirect
		ModelAndView mav = new ModelAndView();
		mav.setViewName("vm");

		// process data
		mockAllocateVM(vm);
		vmService.addVirtualMachine(vm);

		return mav;
	}

	public VirtualMachine mockAllocateVM(VirtualMachine vm) {
		// mock ip
		Random random = new Random();
		StringBuilder ip = new StringBuilder();
		ip.append("192.168.1.");
		ip.append(random.nextInt(254) + 1);
		vm.setIp(ip.toString());
		// mock MAC
		StringBuilder mac = new StringBuilder();
		mac.append("54:04:a6:ad:28:3");
		mac.append(random.nextInt(10));
		vm.setMac(mac.toString());

		return vm;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<String, Object> getVMJson() {
		Map<String, Object> modalMap = new HashMap<String, Object>(2);
		try {
			List<VirtualMachine> vmList = vmService.getVirtualMachineList();
			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			if (!vmList.isEmpty()) {
				for (VirtualMachine vm : vmList) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", vm.getId() + "");
					map.put("name", vm.getName());
					map.put("ip", vm.getIp());
					map.put("cpu", vm.getCpu());
					map.put("memory", vm.getMemory() + "");
					map.put("storage", vm.getStorage() + "");
					map.put("boardWidth", vm.getBoardWidth() + "");
					data.add(map);
				}
			}
			modalMap.put("data", data);
			modalMap.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			modalMap.put("success", "false");
		}
		return modalMap;
	}
}