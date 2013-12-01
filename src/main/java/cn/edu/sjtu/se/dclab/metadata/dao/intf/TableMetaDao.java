package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface TableMetaDao {
	void createTableMeta(TableMeta tablemeta);
	List<TableMeta> getTableMeta(DatabaseMeta dbmeta);
	TableMeta getTableMetaByID(Long id);
	List<TableMeta> getTableMetaByUser(User user);
	void updateTableMeta(TableMeta tablemeta);
	void delTableMeta(TableMeta tablemeta);
	void delTableMetaByID(Long id);
}
