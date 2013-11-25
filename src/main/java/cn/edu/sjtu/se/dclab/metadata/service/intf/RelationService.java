package cn.edu.sjtu.se.dclab.metadata.service.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Relation;

public interface RelationService {
	public void creataRelation(Relation relation);
	public List<Relation> getRelationByDatabase(DatabaseMeta dbmeta);
}
