package cn.edu.sjtu.se.dclab.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.ServerDao;
import cn.edu.sjtu.se.dclab.metadata.service.intf.ServerService;

@Service("serverService")
public class ServerServiceImpl implements ServerService {
	
	@Autowired
	private ServerDao serverDao;

	
	@Transactional
	public List<Server> getServerByUser(User user) {
		return serverDao.getServerByUser(user);
	}
	
	
	@Transactional
	public Server getServerByID(Long id){
		return serverDao.getServerByID(id);
	}
	
	@Transactional
	public void addServer(Server server){
		serverDao.addServer(server);
	}
}
