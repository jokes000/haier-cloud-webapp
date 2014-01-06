package cn.edu.sjtu.se.dclab.metadata.service.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface ServerService {
	public List<Server> getServerByUser(User user);
	public Server getServerByID(Long id);
	public void addServer(Server server);
}
