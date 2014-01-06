package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.ServerDao;

@Repository("serverDao")
public class ServerDaoImpl implements ServerDao {

	@Autowired
	private BaseDao<Server> metaDataBaseDao;
	
	
	public List<Server> getServerByUser(User user) {
		return metaDataBaseDao.queryByProperty(Server.class, "user", user);
	}
	
	
	public Server getServerByID(Long id){
		return metaDataBaseDao.queryById(Server.class, id);
	}

	public void addServer(Server server){
		metaDataBaseDao.save(server);
	}
}
