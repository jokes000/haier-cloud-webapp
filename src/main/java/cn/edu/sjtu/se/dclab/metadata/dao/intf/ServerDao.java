package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface ServerDao {
	List<Server> getServerByUser(User user);

	Server getServerByID(Long id);
	
	void addServer(Server server);
}
