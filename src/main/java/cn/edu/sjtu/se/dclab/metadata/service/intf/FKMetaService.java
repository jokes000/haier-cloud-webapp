package cn.edu.sjtu.se.dclab.metadata.service.intf;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.FKMeta;

public interface FKMetaService {
	public void createFKMeta(FKMeta fkmeta);
	public FKMeta getFKMetaByCol(ColumnMeta columnMeta);
	public void updateFKMeta(FKMeta fkmeta);
	public void delFKMeta(FKMeta fkmeta);
}
