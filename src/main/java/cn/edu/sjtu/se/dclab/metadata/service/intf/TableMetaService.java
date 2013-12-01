package cn.edu.sjtu.se.dclab.metadata.service.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface TableMetaService {
	public void createTableMeta(TableMeta tablemeta);
	public List<TableMeta> getTableMeta(DatabaseMeta dbmeta);
	public TableMeta getTableMetaByID(Long id);
	public List<TableMeta> getTableMetaByUser(User user);
	public void updateTableMeta(TableMeta tablemeta);
	public void delTableMeta(TableMeta tablemeta);
	public void delTableMetaByID(Long id);
}
