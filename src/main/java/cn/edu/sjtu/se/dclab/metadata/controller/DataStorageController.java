package cn.edu.sjtu.se.dclab.metadata.controller;
/*package cn.edu.sjtu.dcl.meta.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.FKMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.User;
import cn.edu.sjtu.se.dclab.metadata.service.intf.ColumnMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DatabaseMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.FKMetaService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.MySqlService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.SqlService;
import cn.edu.sjtu.se.dclab.metadata.service.intf.TableMetaService;

@Controller
@RequestMapping("/datastore")
public class DataStorageController {

	@Autowired
	private DatabaseMetaService databaseMetaService;
	
	@Autowired
	private TableMetaService tableMetaService;
	
	@Autowired
	private ColumnMetaService columnMetaService;
	
	@Autowired
	private FKMetaService fKMetaService;
	
	@Autowired
	private MySqlService mySqlService;
	
	@Autowired
	private SqlService sqlService;
	
	@RequestMapping(value = "", method = RequestMethod.GET) 
	public ModelAndView dataStorage(HttpServletRequest request) { 
		List<TableMeta> tablemeta_list = 
				tableMetaService.getTableMetaByUser((User)request.getSession().getAttribute("user"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("tablemeta_list", tablemeta_list);
		mv.addObject("tableMeta", new TableMeta());
		return mv;
	} 
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createDataStore(@ModelAttribute("tableMeta") TableMeta tableMeta, 
			@RequestParam("column_list") String column_list, 
			@RequestParam("fk_table") String fk_table,
			@RequestParam("fk_table_id") String fk_table_id,
			@RequestParam("fk_column") String fk_column,
			HttpServletRequest request){
		//tableMeta.setDatabasemeta(databaseMetaService.getDatabaseMeta("meta"));
		tableMeta.setUser((User)request.getSession().getAttribute("user"));
		tableMetaService.createTableMeta(tableMeta);
		column_list = column_list.substring(0, column_list.length() - 1);
		column_list += "]";		
		System.out.println(column_list);
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<List<ColumnMeta>>() {
		}.getType();
		List<ColumnMeta> columnList = gson.fromJson(column_list, type);
		FKMeta fkmeta = new FKMeta();
		Iterator<ColumnMeta> it = columnList.iterator();
		for(; it.hasNext(); ){
			ColumnMeta cm = it.next();
			cm.setTablemeta(tableMeta);
			columnMetaService.createColumnMeta(cm);
			if(cm.getC_name().equals(fk_column))
				fkmeta.setColumnmeta_fk(cm);
		}
		if(!fk_table_id.equals("")){
			fkmeta.setTablemeta(tableMetaService.getTableMetaByID(Long.parseLong(fk_table_id)));
			fkmeta.setTablemeta_fk(tableMeta);
			fKMetaService.createFKMeta(fkmeta);
		}
		String sql = "";
		sqlService.createSQL(tableMeta.getT_name(), columnList, fk_table, fk_column);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("sql", sql);
		return mv;
		
		
		
		InputStream inputStream = 
				this.getClass().getClassLoader().getResourceAsStream("datastore.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = p.getProperty("MySql.url");
		String user = p.getProperty("MySql.username");
		String password = p.getProperty("MySql.password");
		
		Connection con = mySqlService.openCon(url, user, password);
		mySqlService.createTable(con, tableMeta, columnList, pk_column);
		mySqlService.closeCon(con);		
	}

}
*/