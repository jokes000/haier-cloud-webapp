package cn.edu.sjtu.se.dclab.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.ColumnMetaDao;
import cn.edu.sjtu.se.dclab.metadata.service.intf.ColumnMetaService;

@Service("columnMetaService")
public class ColumnMetaServiceImpl implements ColumnMetaService {

	@Autowired
	private ColumnMetaDao columnMetaDao;
	
	
	@Transactional
	public void createColumnMeta(ColumnMeta columnMeta) {
		columnMetaDao.createColumnMeta(columnMeta);

	}
	
	
	@Transactional
	public List<ColumnMeta> getColumnMeta(TableMeta tablemeta){
		return columnMetaDao.getColumnMeta(tablemeta);
	}
	
	
	@Transactional
	public void updateColumnMeta(ColumnMeta columnMeta){
		columnMetaDao.updateColumnMeta(columnMeta);
	}
	
	
	@Transactional
	public ColumnMeta getColumnMetaByID(Long id){
		return columnMetaDao.getColumnMetaByID(id);
	}
	
	
	@Transactional
	public void delColumnMeta(ColumnMeta columnMeta){
		columnMetaDao.delColumnMeta(columnMeta);
	}
}
