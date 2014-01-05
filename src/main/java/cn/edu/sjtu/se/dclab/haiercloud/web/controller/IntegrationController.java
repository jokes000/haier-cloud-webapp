package cn.edu.sjtu.se.dclab.haiercloud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.sjtu.se.dclab.codegen.integration.IntegrateAll;

@Controller
public class IntegrationController {

	@RequestMapping(value = "/codegen", method = RequestMethod.GET)
	public void test() {
		IntegrateAll ia = new IntegrateAll();
		ia.integrate();
	}
	
}
