package cn.edu.sjtu.se.dclab.haiercloud.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dclab.haiercloud.web.auth.CommonVariableModel;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IAuthService;

@Controller
public class AccountController {

//	@Resource(name = "authService")
//	private IAuthService authService;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView account() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/account");

		Subject user = SecurityUtils.getSubject();
		CommonVariableModel model = (CommonVariableModel)user.getPrincipal();
		
		mav.addObject("user", model.getUser());
		
		return mav;
	}
	
}
