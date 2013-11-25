package cn.edu.sjtu.se.dclab.metadata.service.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;

public interface DatabaseMetaService {
	public void createDatabaseMeta(DatabaseMeta databasemeta);
	public DatabaseMeta getDatabaseMeta(String db_name);
	public List<DatabaseMeta> getAllDatabaseMeta();
	public List<DatabaseMeta> getDatabaseMetaByServer(Server server);
}
