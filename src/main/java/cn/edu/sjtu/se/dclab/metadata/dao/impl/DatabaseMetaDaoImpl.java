package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.DatabaseMetaDao;

@Repository("databaseMetaDao")
public class DatabaseMetaDaoImpl implements DatabaseMetaDao {
	
	@Autowired
	private BaseDao<DatabaseMeta> metaDataBaseDao;
	
	
	public void createDatabaseMeta(DatabaseMeta databasemeta) {		
		metaDataBaseDao.save(databasemeta);		
	}
	
	
	public DatabaseMeta getDatabaseMeta(String db_name){
		DatabaseMeta dbmeta = (DatabaseMeta) metaDataBaseDao.queryByProperty(
				DatabaseMeta.class, "db_name", db_name).get(0);
		return dbmeta;
	}
	
	
	public List<DatabaseMeta> getAllDatabaseMeta(){
		List<DatabaseMeta> dbmeta_list= metaDataBaseDao.queryAll(DatabaseMeta.class);
		return dbmeta_list;
	}
	
	
	public List<DatabaseMeta> getDatabaseMetaByServer(Server server){
		return metaDataBaseDao.queryByProperty(DatabaseMeta.class, "server", server);
	}
}
