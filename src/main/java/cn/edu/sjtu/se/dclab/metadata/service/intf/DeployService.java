package cn.edu.sjtu.se.dclab.metadata.service.intf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.Deploy;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface DeployService {
	public String createMysql(TableMeta tablemeta, String dbname, 
			Server server, HttpServletRequest request);
	public String createMongo(TableMeta tablemeta, String dbname, 
			Server server, HttpServletRequest request);
	public String createMysqlDB(String dbname, Server server, HttpServletRequest request);
	public void createDeploy(Deploy deploy);
	public List<Deploy> getDeployByUser(User user);
	public Deploy getDeployByID(Long id);
	public void updateDeploy(Deploy deploy);
}
