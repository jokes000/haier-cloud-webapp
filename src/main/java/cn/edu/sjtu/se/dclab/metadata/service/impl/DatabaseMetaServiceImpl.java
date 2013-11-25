package cn.edu.sjtu.se.dclab.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.DatabaseMetaDao;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DatabaseMetaService;

@Service("databaseMetaService")
public class DatabaseMetaServiceImpl implements DatabaseMetaService {
	
	@Autowired
	private DatabaseMetaDao databaseMetaDao;
	
	
	@Transactional
	public void createDatabaseMeta(DatabaseMeta databasemeta) {
		databaseMetaDao.createDatabaseMeta(databasemeta);
	}
	
	
	@Transactional
	public DatabaseMeta getDatabaseMeta(String db_name){
		return databaseMetaDao.getDatabaseMeta(db_name);
	}
	
	
	@Transactional
	public List<DatabaseMeta> getAllDatabaseMeta(){
		return databaseMetaDao.getAllDatabaseMeta();
	}

	
	@Transactional
	public List<DatabaseMeta> getDatabaseMetaByServer(Server server){
		return databaseMetaDao.getDatabaseMetaByServer(server);
	}
}
