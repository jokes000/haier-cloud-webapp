package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.FKMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.FKMetaDao;

@Repository("fKMetaDao")
public class FKMetaDaoImpl implements FKMetaDao {

	@Autowired
	private BaseDao<FKMeta> metaDataBaseDao;
	
	
	public void createFKMeta(FKMeta fkmeta) {
		metaDataBaseDao.save(fkmeta);
	}
	
	
	public FKMeta getFKMetaByCol(ColumnMeta columnMeta){
		List<FKMeta> list = metaDataBaseDao.queryByProperty(FKMeta.class, "columnmeta_fk", columnMeta);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	
	public void updateFKMeta(FKMeta fkmeta){
		metaDataBaseDao.update(fkmeta);
	}
	
	
	public void delFKMeta(FKMeta fkmeta){
		metaDataBaseDao.delete(fkmeta);
	}

}
