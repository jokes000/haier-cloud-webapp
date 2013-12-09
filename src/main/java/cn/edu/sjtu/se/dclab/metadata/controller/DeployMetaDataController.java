package cn.edu.sjtu.se.dclab.metadata.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Deploy;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Relation;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.service.intf.ColumnMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DatabaseMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DeployService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.RelationService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.ServerService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.TableMetaService;

@Controller
@RequestMapping("/metadata/deploy")
public class DeployMetaDataController {
	
	@Autowired
	private DatabaseMetaService databaseMetaService;
	
	@Autowired
	private TableMetaService tableMetaService;
	
	@Autowired
	private ColumnMetaService columnMetaService;
	
	@Autowired
	private DeployService deployService;
	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private RelationService relationService;
	
	@RequestMapping(value = "", method = RequestMethod.GET) 
	public ModelAndView deploy(HttpServletRequest request) { 
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		List<Server> server_list = serverService.getServerByUser((User)request.getSession().getAttribute("user"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("tableMeta", new TableMeta());
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("server_list", server_list);
		return mv;
	}
	
	@RequestMapping(value="info", method = RequestMethod.GET)
	public ModelAndView deployInfo(HttpServletRequest request) { 
		List<Deploy> deploy_list = 
				deployService.getDeployByUser((User)request.getSession().getAttribute("user"));		
		ModelAndView mv = new ModelAndView();
		mv.addObject("deploy_list", deploy_list);
		return mv;
	}
	
	@RequestMapping(value="manage", method = RequestMethod.GET)
	public ModelAndView deployManage(HttpServletRequest request) { 
		List<Deploy> deploy_list = 
				deployService.getDeployByUser((User)request.getSession().getAttribute("user"));		
		ModelAndView mv = new ModelAndView();
		mv.addObject("deploy", new Deploy());
		mv.addObject("deploy_list", deploy_list);
		return mv;
	}
	
	@RequestMapping(value="manage/modify", method = RequestMethod.POST)
	public ModelAndView deployModify(@ModelAttribute("deploy") Deploy deploy, HttpServletRequest request) { 
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		List<TableMeta> deployed_tablemeta = new ArrayList<TableMeta>();
		Server server = deployService.getDeployByID(deploy.getId()).getServer();
		deploy = deployService.getDeployByID(deploy.getId());
		List<Relation> relation = relationService.getRelationByDatabase(deploy.getDatabaseMeta());
		String dbname = deploy.getDatabaseMeta().getDb_name();
		Iterator<Relation> it = relation.iterator();
		for(; it.hasNext();){
			Relation r = it.next();			
			for(TableMeta tm:tablemeta_list){
				if(tm.getId().equals(r.getTableMeta().getId()))
					deployed_tablemeta.add(tm);
			}
		}
		
		tablemeta_list.removeAll(deployed_tablemeta);
		ModelAndView mv = new ModelAndView();
		mv.addObject("deploy", deploy);
		mv.addObject("server", server);
		mv.addObject("deployed_tablemeta", deployed_tablemeta);
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("dbname", dbname);
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addDeploy(@RequestParam("database_name") String dbname,@RequestParam("server_id") String serverid,
			@RequestParam("table") List<String> tableid_list, HttpServletRequest request){	
		ModelAndView mv = new ModelAndView();

		Server server = serverService.getServerByID(Long.parseLong(serverid));
		
		List<DatabaseMeta> db_list = databaseMetaService.getDatabaseMetaByServer(server);
		for(DatabaseMeta dm : db_list){
			if(dbname.equals(dm.getDb_name())){
				mv.setViewName("metadata/deploy/fail_duplicatedb");
				return mv;
			}
		}
		
		String status = "success";
		String msg = "";
		DatabaseMeta dbmeta = new DatabaseMeta();
		dbmeta.setDb_name(dbname);
		dbmeta.setUser((User) request.getSession().getAttribute("user"));
		dbmeta.setServer(server);
		databaseMetaService.createDatabaseMeta(dbmeta);
				
		Deploy deploy = new Deploy();
		deploy.setUser((User)request.getSession().getAttribute("user"));
		deploy.setDatabaseMeta(dbmeta);
		deploy.setServer(server);
		
		if (server.getType().equals("mysql")) {
			msg = deployService.createMysqlDB(dbname, server, request);
			if(!msg.equals("success")){
				deploy.setMsg(msg);
				status = "fail";
				deploy.setStatus(status);
				deployService.createDeploy(deploy);	
				mv.setViewName("metadata/deploy/fail");
				return mv;
			}
			Iterator<String> it = tableid_list.iterator();
			for(; it.hasNext();){
				String tid = it.next();
				TableMeta tm = tableMetaService.getTableMetaByID(Long.parseLong(tid));				
				msg = deployService.createMysql(tm, dbname, server, request);
				if(msg.equals("success")){
					status = "success";
					mv.setViewName("metadata/deploy/success");
					Relation relation = new Relation();
					relation.setDatabaseMeta(dbmeta);
					relation.setTableMeta(tm);
					relationService.creataRelation(relation);
				}else {
					status = "fail";
					mv.setViewName("metadata/deploy/fail");
					break;
				}				
			}
			deploy.setMsg(msg);
			deploy.setStatus(status);
			deployService.createDeploy(deploy);				
		}
		if(server.getType().equals("mongodb")){
			Iterator<String> it = tableid_list.iterator();
			for(; it.hasNext();){
				String tid = it.next();
				TableMeta tm = tableMetaService.getTableMetaByID(Long.parseLong(tid));
				msg = deployService.createMongo(tm, dbname, server, request);
				if(msg.equals("success")){
					status = "success";
					mv.setViewName("metadata/deploy/success");
					Relation relation = new Relation();
					relation.setDatabaseMeta(dbmeta);
					relation.setTableMeta(tm);
					relationService.creataRelation(relation);
				}else {
					status = "fail";
					mv.setViewName("metadata/deploy/fail");
					break;
				}				
			}
			deploy.setMsg(msg);
			deploy.setStatus(status);
			deployService.createDeploy(deploy);	
		}
		return mv;
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public ModelAndView changeDeploy(@RequestParam("database_name") String dbname,@RequestParam("server") String serverid,
			@RequestParam("table") List<String> tableid_list, @RequestParam("deploy") String deployid, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String status = "success";
		String msg = "";
		Server server = serverService.getServerByID(Long.parseLong(serverid));
		Deploy deploy = deployService.getDeployByID(Long.parseLong(deployid));
		DatabaseMeta dbmeta = deploy.getDatabaseMeta();
		
		
		if (server.getType().equals("mysql")) {
			Iterator<String> it = tableid_list.iterator();
			for(; it.hasNext();){
				String tid = it.next();
				TableMeta tm = tableMetaService.getTableMetaByID(Long.parseLong(tid));
				msg = deployService.createMysql(tm, dbname, server, request);
				if(msg.equals("success")){
					status = "success";
					mv.setViewName("metadata/deploy/success");
					Relation relation = new Relation();
					relation.setDatabaseMeta(dbmeta);
					relation.setTableMeta(tm);
					relationService.creataRelation(relation);
				}else {
					status = "fail";
					mv.setViewName("metadata/deploy/fail");
					break;
				}				
			}
			deploy.setMsg(msg);
			deploy.setStatus(status);
			deployService.updateDeploy(deploy);
		}
		if(server.getType().equals("mongodb")){
			Iterator<String> it = tableid_list.iterator();
			for(; it.hasNext();){
				String tid = it.next();
				TableMeta tm = tableMetaService.getTableMetaByID(Long.parseLong(tid));
				msg = deployService.createMongo(tm, dbname, server, request);
				if(msg.equals("success")){
					status = "success";
					mv.setViewName("metadata/deploy/success");
					Relation relation = new Relation();
					relation.setDatabaseMeta(dbmeta);
					relation.setTableMeta(tm);
					relationService.creataRelation(relation);
				}else {
					status = "fail";
					mv.setViewName("metadata/deploy/fail");
					break;
				}				
			}
			deploy.setMsg(msg);
			deploy.setStatus(status);
			deployService.updateDeploy(deploy);	
		}
		
		
		return mv;
	}
}
