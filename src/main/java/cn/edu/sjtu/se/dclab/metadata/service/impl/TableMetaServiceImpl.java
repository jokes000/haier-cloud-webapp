package cn.edu.sjtu.se.dclab.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.TableMetaDao;
import cn.edu.sjtu.se.dclab.metadata.service.intf.TableMetaService;

@Service("tableMetaService")
public class TableMetaServiceImpl implements TableMetaService {

	@Autowired
	private TableMetaDao tableMetaDao;
	
	
	@Transactional
	public void createTableMeta(TableMeta tablemeta) {
		tableMetaDao.createTableMeta(tablemeta);

	}
	
	
	@Transactional
	public List<TableMeta> getTableMeta(DatabaseMeta dbmeta){
		return tableMetaDao.getTableMeta(dbmeta);
	}
	
	
	@Transactional
	public TableMeta getTableMetaByID(Long id){
		return tableMetaDao.getTableMetaByID(id);
	}
	
	
	@Transactional
	public List<TableMeta> getTableMetaByUser(User user){
		return tableMetaDao.getTableMetaByUser(user);
	}
	
	
	@Transactional
	public void updateTableMeta(TableMeta tablemeta){
		tableMetaDao.updateTableMeta(tablemeta);
	}
	
	
	@Transactional
	public void delTableMeta(TableMeta tablemeta){
		tableMetaDao.delTableMeta(tablemeta);
	}
	
	
	@Transactional
	public void delTableMetaByID(Long id){
		tableMetaDao.delTableMetaByID(id);
	}
}
