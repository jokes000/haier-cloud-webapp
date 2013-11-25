package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;

public interface ColumnMetaDao {
	void createColumnMeta(ColumnMeta columnMeta);
	List<ColumnMeta> getColumnMeta(TableMeta tablemeta);
	void updateColumnMeta(ColumnMeta columnMeta);
	ColumnMeta getColumnMetaByID(Long id);
	void delColumnMeta(ColumnMeta columnMeta);
}
