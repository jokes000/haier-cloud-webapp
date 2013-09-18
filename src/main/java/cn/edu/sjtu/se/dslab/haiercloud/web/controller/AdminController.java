package cn.edu.sjtu.se.dslab.haiercloud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/group", method = RequestMethod.GET)
	public ModelAndView groupView() {
		ModelAndView mav  = new ModelAndView();
		mav.setViewName("/admin/group");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/group/add", method = RequestMethod.GET)
	public ModelAndView addGroup() {
		
		ModelAndView mav  = new ModelAndView();
		mav.setViewName("/admin/group/add");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/group/delete", method = RequestMethod.GET)
	public ModelAndView deleteGroup() {
		
		return null;
	}
	
	@RequestMapping(value="/admin/user/", method = RequestMethod.GET)
	public ModelAndView userView() {
		
		ModelAndView mav  = new ModelAndView();
		mav.setViewName("/admin/user");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/user/add", method = RequestMethod.GET)
	public ModelAndView addUser() {
		
		ModelAndView mav  = new ModelAndView();
		mav.setViewName("/admin/user/add");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/user/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser() {
		
		return null;
	}
	
	@RequestMapping(value="/admin/permission", method = RequestMethod.GET)
	public ModelAndView permissionView() {
		
		ModelAndView mav  = new ModelAndView();
		mav.setViewName("/admin/permission");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/permission/add", method = RequestMethod.GET)
	public ModelAndView addPermission() {
		
		ModelAndView mav  = new ModelAndView();
		mav.setViewName("/admin/permission/add");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/permission/delete", method = RequestMethod.GET)
	public ModelAndView deletePermission() {
		
		return null;
	}
	
}
