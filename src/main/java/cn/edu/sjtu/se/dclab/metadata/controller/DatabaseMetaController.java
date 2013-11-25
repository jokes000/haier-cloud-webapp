package cn.edu.sjtu.se.dclab.metadata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DatabaseMetaService;


@Controller
@RequestMapping("/metadata")
public class DatabaseMetaController {

	@Autowired
	private DatabaseMetaService databaseMetaService;
	
	@RequestMapping(value = "/dbmeta", method = RequestMethod.GET) 
	public ModelAndView getDbMeta() { 
		List<DatabaseMeta> dbmeta_list = databaseMetaService.getAllDatabaseMeta();
		ModelAndView mv = new ModelAndView();
		mv.addObject("dbmeta_list", dbmeta_list);
		mv.addObject("databaseMeta", new DatabaseMeta());
		return mv;
	} 
	
}
