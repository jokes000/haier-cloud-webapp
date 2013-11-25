package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;

public interface DatabaseMetaDao {	
	void createDatabaseMeta(DatabaseMeta databasemeta);	
	DatabaseMeta getDatabaseMeta(String db_name);
	List<DatabaseMeta> getAllDatabaseMeta();
	List<DatabaseMeta> getDatabaseMetaByServer(Server server);
}
