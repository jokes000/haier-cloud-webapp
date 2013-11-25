package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.Deploy;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.DeployDao;

@Repository("deployDao")
public class DeployDaoImpl implements DeployDao {
	
	@Autowired
	private BaseDao<Deploy> metaDataBaseDao;

	
	public void creataDeploy(Deploy deploy) {
		metaDataBaseDao.save(deploy);
	}
	
	
	public List<Deploy> getDeployByUser(User user){
		return metaDataBaseDao.queryByProperty(Deploy.class, "user", user);
	}
	
	
	public Deploy getDeployByID(Long id){
		return metaDataBaseDao.queryById(Deploy.class, id);
	}
	
	
	public void updateDeploy(Deploy deploy){
		metaDataBaseDao.update(deploy);
	}

}
