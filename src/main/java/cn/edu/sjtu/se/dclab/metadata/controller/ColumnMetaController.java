package cn.edu.sjtu.se.dclab.metadata.controller;

import java.util.ArrayList;
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

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.FKMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.service.intf.ColumnMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DatabaseMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.FKMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.TableMetaService;

@Controller
@RequestMapping("/metadata")
public class ColumnMetaController {

	@Autowired
	private DatabaseMetaService databaseMetaService;
	
	@Autowired
	private TableMetaService tableMetaService;
	
	@Autowired
	private ColumnMetaService columnMetaService;
	
	@Autowired
	private FKMetaService fKMetaService;

	@RequestMapping(value = "/columnmeta", method = RequestMethod.POST)  
	public ModelAndView getColumnMeta(@ModelAttribute("tableMeta") TableMeta tablemeta, BindingResult result,
			HttpServletRequest request){	
		tablemeta.setT_name(tableMetaService.getTableMetaByID(tablemeta.getId()).getT_name());
		List<ColumnMeta> columnmeta_list = columnMetaService.getColumnMeta(tablemeta);
		//DatabaseMeta dbmeta = databaseMetaService.getDatabaseMeta("meta");
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		Iterator<ColumnMeta> it = columnmeta_list.iterator();
		List<String> fk_list = new ArrayList<String>();
		for(; it.hasNext(); ){
			ColumnMeta cm = it.next();
			FKMeta fm = fKMetaService.getFKMetaByCol(cm);
			if(fm == null)
				fk_list.add("");
			else
				fk_list.add(fm.getTablemeta().getT_name());
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("table", tablemeta);
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("fk_list", fk_list);
		mv.addObject("columnmeta_list", columnmeta_list);
		mv.addObject("columnMeta", new ColumnMeta());
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	}

	@RequestMapping(value = "/columnmeta/edit", method = RequestMethod.POST)
	public ModelAndView editColumnMeta(@ModelAttribute("columnMeta") ColumnMeta columnmeta, BindingResult result, 
								@RequestParam("tid") Long id, @RequestParam("fk") String fk, HttpServletRequest request){	
		TableMeta tablemeta = tableMetaService.getTableMetaByID(id);
		columnmeta.setTablemeta(tablemeta);
		System.out.println(columnmeta.getId());
		System.out.println(columnmeta.getC_name());
		System.out.println(columnmeta.getType());
		columnMetaService.updateColumnMeta(columnmeta);
		FKMeta fkmeta = fKMetaService.getFKMetaByCol(columnmeta);
		if (fk == "") {
			if (fkmeta != null)
				fKMetaService.delFKMeta(fkmeta);
		} else {
			if (fkmeta != null) {
				fkmeta.setTablemeta(tableMetaService.getTableMetaByID(Long
						.parseLong(fk)));
				fKMetaService.updateFKMeta(fkmeta);
			} else {
				FKMeta f = new FKMeta();
				f.setColumnmeta_fk(columnmeta);
				f.setTablemeta(tableMetaService.getTableMetaByID(Long
						.parseLong(fk)));
				f.setTablemeta_fk(tablemeta);
				fKMetaService.createFKMeta(f);
			}
		}
		
		List<ColumnMeta> columnmeta_list = columnMetaService.getColumnMeta(tablemeta);
		//DatabaseMeta dbmeta = databaseMetaService.getDatabaseMeta("meta");
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		Iterator<ColumnMeta> it = columnmeta_list.iterator();
		List<String> fk_list = new ArrayList<String>();
		for(; it.hasNext(); ){
			ColumnMeta cm = it.next();
			FKMeta fm = fKMetaService.getFKMetaByCol(cm);
			if(fm == null)
				fk_list.add("");
			else
				fk_list.add(fm.getTablemeta().getT_name());
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("table", tablemeta);
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("fk_list", fk_list);
		mv.addObject("columnmeta_list", columnmeta_list);
		mv.addObject("columnMeta", new ColumnMeta());
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	}
	
	@RequestMapping(value = "/columnmeta/del", method = RequestMethod.POST)
	public ModelAndView delColumnMeta(@ModelAttribute("columnMeta") ColumnMeta columnmeta, BindingResult result, 
								@RequestParam("tid") Long id, @RequestParam("fk") String fk, HttpServletRequest request){	
		TableMeta tablemeta = tableMetaService.getTableMetaByID(id);
		FKMeta fkmeta = fKMetaService.getFKMetaByCol(columnmeta);

		if (fkmeta != null)
			fKMetaService.delFKMeta(fkmeta);
		columnMetaService.delColumnMeta(columnmeta);
		
		List<ColumnMeta> columnmeta_list = columnMetaService.getColumnMeta(tablemeta);
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		Iterator<ColumnMeta> it = columnmeta_list.iterator();
		List<String> fk_list = new ArrayList<String>();
		for(; it.hasNext(); ){
			ColumnMeta cm = it.next();
			FKMeta fm = fKMetaService.getFKMetaByCol(cm);
			if(fm == null)
				fk_list.add("");
			else
				fk_list.add(fm.getTablemeta().getT_name());
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("table", tablemeta);
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("fk_list", fk_list);
		mv.addObject("columnmeta_list", columnmeta_list);
		mv.addObject("columnMeta", new ColumnMeta());
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	}

	@RequestMapping(value = "/columnmeta/edittable", method = RequestMethod.POST)
	public ModelAndView editTable(@ModelAttribute("tableMeta") TableMeta tablemeta, BindingResult result,
			HttpServletRequest request){	
		TableMeta tm = tableMetaService.getTableMetaByID(tablemeta.getId());
		tm.setT_name(tablemeta.getT_name());
		tableMetaService.updateTableMeta(tm);
		
		List<ColumnMeta> columnmeta_list = columnMetaService.getColumnMeta(tablemeta);
		//DatabaseMeta dbmeta = databaseMetaService.getDatabaseMeta("meta");
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		Iterator<ColumnMeta> it = columnmeta_list.iterator();
		List<String> fk_list = new ArrayList<String>();
		for(; it.hasNext(); ){
			ColumnMeta cm = it.next();
			FKMeta fm = fKMetaService.getFKMetaByCol(cm);
			if(fm == null)
				fk_list.add("");
			else
				fk_list.add(fm.getTablemeta().getT_name());
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("table", tablemeta);
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("fk_list", fk_list);
		mv.addObject("columnmeta_list", columnmeta_list);
		mv.addObject("columnMeta", new ColumnMeta());
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	}
	
	@RequestMapping(value = "/columnmeta/new", method = RequestMethod.POST)  
	public ModelAndView newColumn(@ModelAttribute("columnMeta") ColumnMeta columnmeta, BindingResult result, 
			@RequestParam("tid") Long id, @RequestParam("fk") String fk,
			HttpServletRequest request){
		TableMeta tablemeta = tableMetaService.getTableMetaByID(id);
		columnmeta.setTablemeta(tablemeta);
		columnMetaService.createColumnMeta(columnmeta);
		if(fk !=""){
			FKMeta f = new FKMeta();
			f.setColumnmeta_fk(columnmeta);
			f.setTablemeta(tableMetaService.getTableMetaByID(Long.parseLong(fk)));
			f.setTablemeta_fk(tablemeta);
			fKMetaService.createFKMeta(f);
		}
		
		List<ColumnMeta> columnmeta_list = columnMetaService.getColumnMeta(tablemeta);
		//DatabaseMeta dbmeta = databaseMetaService.getDatabaseMeta("meta");
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		Iterator<ColumnMeta> it = columnmeta_list.iterator();
		List<String> fk_list = new ArrayList<String>();
		for(; it.hasNext(); ){
			ColumnMeta cm = it.next();
			FKMeta fm = fKMetaService.getFKMetaByCol(cm);
			if(fm == null)
				fk_list.add("");
			else
				fk_list.add(fm.getTablemeta().getT_name());
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("table", tablemeta);
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("fk_list", fk_list);
		mv.addObject("columnmeta_list", columnmeta_list);
		mv.addObject("columnMeta", new ColumnMeta());
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	}
	
}
