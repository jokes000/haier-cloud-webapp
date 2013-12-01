package cn.edu.sjtu.se.dclab.metadata.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DatabaseMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.TableMetaService;

@Controller
@RequestMapping("/metadata")
public class TableMetaController {
	
	@Autowired
	private TableMetaService tableMetaService;
	
	@Autowired
	private DatabaseMetaService databaseMetaService;

	@RequestMapping(value = "/tablemeta", method = RequestMethod.GET)  
	public ModelAndView getTableMeta(//@ModelAttribute("databaseMeta") DatabaseMeta dbmeta, BindingResult result,
										HttpServletRequest request){	
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	}

	@RequestMapping(value = "/tablemeta/del", method = RequestMethod.POST)
	public ModelAndView editTableMeta(
			@ModelAttribute("tableMeta") TableMeta tablemeta,
			BindingResult result, HttpServletRequest request) {
		tableMetaService.delTableMetaByID(tablemeta.getId());

		List<TableMeta> tablemeta_list = tableMetaService
				.getTableMetaByUser((User) request.getSession().getAttribute(
						"user"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("tableMeta", new TableMeta());
		return mv;

	}
	
	@RequestMapping(value = "/tablemeta/create", method = RequestMethod.POST)
	public ModelAndView createTableMeta(@ModelAttribute("tableMeta") TableMeta tablemeta,
			BindingResult result, HttpServletRequest request){
		tablemeta.setUser((User) request.getSession().getAttribute(
						"user"));
		//tablemeta.setDatabasemeta(databaseMetaService.getDatabaseMeta("meta"));
		tableMetaService.createTableMeta(tablemeta);
		
		List<TableMeta> tablemeta_list = tableMetaService
				.getTableMetaByUser((User) request.getSession().getAttribute(
						"user"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	}
}
