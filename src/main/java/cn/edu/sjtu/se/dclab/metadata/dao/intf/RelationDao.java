package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.DatabaseMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Relation;

public interface RelationDao {
	void creataRelation(Relation relation);
	List<Relation> getRelationByDatabase(DatabaseMeta dbmeta);
}
