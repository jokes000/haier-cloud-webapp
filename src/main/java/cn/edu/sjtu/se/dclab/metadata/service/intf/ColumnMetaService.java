package cn.edu.sjtu.se.dclab.metadata.service.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;

public interface ColumnMetaService {
	public void createColumnMeta(ColumnMeta columnMeta);
	public List<ColumnMeta> getColumnMeta(TableMeta tablemeta);
	public void updateColumnMeta(ColumnMeta columnMeta);
	public ColumnMeta getColumnMetaByID(Long id);
	public void delColumnMeta(ColumnMeta columnMeta);
}
