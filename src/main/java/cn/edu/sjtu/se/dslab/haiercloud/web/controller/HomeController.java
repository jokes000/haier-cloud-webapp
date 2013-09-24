/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-16 16:19
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.controller;

// Spring support
import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IAuthService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterMetaService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterService;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IVirtualMachineService;

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

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String redirectLogin() {
        
    	if (!authService.isAuthenticated()) {
    		return "redirect:/login";
    	}
    	
    	return "redirect:/home";
    }
    
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        // configure redirect
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/home");
        
        return mav;
    }

    @RequestMapping(value = "/cluster", method = RequestMethod.GET)
    public ModelAndView getClusterList(@RequestParam(value = "page", defaultValue="1") int page) {
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
    public ModelAndView vmManagement(@RequestParam(value = "page", defaultValue="1") int page) {
        // configure redirect
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/vm");

        // get data
        // add data
        int pageSize = 10;
        
        long totalNumber = vmService.totalNumber();
        mav.addObject("total", totalNumber);
        
        List<VirtualMachine> list = vmService.getByPage(page, pageSize);
        mav.addObject("vmList", list);
        
        mav.addObject("page", page);
        mav.addObject("pageSize", pageSize);
        
        return mav;
    }
}