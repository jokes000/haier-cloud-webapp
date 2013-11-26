package cn.edu.sjtu.se.dclab.metadata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IAuthService;


@Controller
public class BaseController {
	
	@Autowired
	private IAuthService userService;
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET) 
	public ModelAndView setDbMeta(HttpServletRequest request) { 
		//save user to session
		//User user = userService.getUserById(1);
		//request.getSession().setAttribute("user", user);
		return new ModelAndView("metabase"); 
	} 

}
