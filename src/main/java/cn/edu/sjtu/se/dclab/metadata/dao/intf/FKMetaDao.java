package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.FKMeta;

public interface FKMetaDao {
	void createFKMeta(FKMeta fkmeta);
	FKMeta getFKMetaByCol(ColumnMeta columnMeta);
	void updateFKMeta(FKMeta fkmeta);
	void delFKMeta(FKMeta fkmeta);
}
