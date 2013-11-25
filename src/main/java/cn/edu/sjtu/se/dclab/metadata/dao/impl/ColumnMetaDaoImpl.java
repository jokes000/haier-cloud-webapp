package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.ColumnMetaDao;

@Repository("columnMetaDao")
public class ColumnMetaDaoImpl implements ColumnMetaDao {

	@Autowired
	private BaseDao<ColumnMeta> metaDataBaseDao;
	
	
	public void createColumnMeta(ColumnMeta columnMeta) {
		metaDataBaseDao.save(columnMeta);
	}
	
	
	public List<ColumnMeta> getColumnMeta(TableMeta tablemeta){
		return metaDataBaseDao.queryByProperty(ColumnMeta.class, "tablemeta", tablemeta);
	}
	
	
	public void updateColumnMeta(ColumnMeta columnMeta){
		metaDataBaseDao.update(columnMeta);
	}
	
	
	public ColumnMeta getColumnMetaByID(Long id){
		return metaDataBaseDao.queryById(ColumnMeta.class, id);
	}
	
	
	public void delColumnMeta(ColumnMeta columnMeta){
		metaDataBaseDao.delete(columnMeta);
	}
}
