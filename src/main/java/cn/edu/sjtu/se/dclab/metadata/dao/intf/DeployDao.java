package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import java.util.List;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.Deploy;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface DeployDao {
	void creataDeploy(Deploy deploy);
	List<Deploy> getDeployByUser(User user);
	Deploy getDeployByID(Long id);
	void updateDeploy(Deploy deploy);
}
