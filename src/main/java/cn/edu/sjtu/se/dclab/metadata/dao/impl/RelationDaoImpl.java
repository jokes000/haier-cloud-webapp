package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Relation;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.RelationDao;

@Repository("relationDao")
public class RelationDaoImpl implements RelationDao {
	
	@Autowired
	private BaseDao<Relation> metaDataBaseDao;

	
	public void creataRelation(Relation relation) {
		metaDataBaseDao.save(relation);
	}
	
	
	public List<Relation> getRelationByDatabase(DatabaseMeta dbmeta){
		return metaDataBaseDao.queryByProperty(Relation.class, "databaseMeta", dbmeta);
	}

}
