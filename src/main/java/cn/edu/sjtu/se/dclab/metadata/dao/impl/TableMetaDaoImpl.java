package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.TableMetaDao;

@Repository("tableMetaDao")
public class TableMetaDaoImpl implements TableMetaDao {
	
	@Autowired
	private BaseDao<TableMeta> metaDataBaseDao;

	
	public void createTableMeta(TableMeta tablemeta) {
		metaDataBaseDao.save(tablemeta);
	}
	
	
	public List<TableMeta> getTableMeta(DatabaseMeta dbmeta){
		return metaDataBaseDao.queryByProperty(TableMeta.class, "databasemeta", dbmeta);
	}

	
	public TableMeta getTableMetaByID(Long id){
		return metaDataBaseDao.queryById(TableMeta.class, id);
	}
	
	
	public List<TableMeta> getTableMetaByUser(User user){
		return metaDataBaseDao.queryByProperty(TableMeta.class, "user", user);
	}
	
	
	public void updateTableMeta(TableMeta tablemeta){
		metaDataBaseDao.update(tablemeta);
	}
	
	
	public void delTableMeta(TableMeta tablemeta){
		metaDataBaseDao.delete(tablemeta);
	}
	
	
	public void delTableMetaByID(Long id){
		metaDataBaseDao.deleteById(TableMeta.class, id);
	}
}
