package cn.edu.sjtu.se.dclab.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Relation;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.RelationDao;
import cn.edu.sjtu.se.dclab.metadata.service.intf.RelationService;

@Service("relationService")
public class RelationServiceImpl implements RelationService {

	@Autowired
	private RelationDao relationDao;
	
	
	@Transactional
	public void creataRelation(Relation relation) {
		relationDao.creataRelation(relation);

	}
	
	
	@Transactional
	public List<Relation> getRelationByDatabase(DatabaseMeta dbmeta){
		return relationDao.getRelationByDatabase(dbmeta);
	}

}
