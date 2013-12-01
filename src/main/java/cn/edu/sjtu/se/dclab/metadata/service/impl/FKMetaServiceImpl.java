package cn.edu.sjtu.se.dclab.metadata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.FKMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.FKMetaDao;
import cn.edu.sjtu.se.dclab.metadata.service.intf.FKMetaService;

@Service("fKService")
public class FKMetaServiceImpl implements FKMetaService {

	@Autowired
	private FKMetaDao fKMetaDao;
	
	
	@Transactional
	public void createFKMeta(FKMeta fkmeta) {
		fKMetaDao.createFKMeta(fkmeta);

	}
	
	
	@Transactional
	public FKMeta getFKMetaByCol(ColumnMeta columnMeta){
		return fKMetaDao.getFKMetaByCol(columnMeta);
	}
	
	
	@Transactional
	public void updateFKMeta(FKMeta fkmeta){
		fKMetaDao.updateFKMeta(fkmeta);
	}
	
	
	@Transactional
	public void delFKMeta(FKMeta fkmeta){
		fKMetaDao.delFKMeta(fkmeta);
	}
}
