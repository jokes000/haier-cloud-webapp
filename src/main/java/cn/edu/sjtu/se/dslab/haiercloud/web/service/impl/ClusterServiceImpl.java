/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-13 19:54
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IClusterDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterService;

@Service("clusterService")
@Transactional
public class ClusterServiceImpl implements IClusterService {

	// Properties
	@Resource(name = "clusterDao")
	private IClusterDao dao;

	// implemented methods
	public void addCluster(Cluster cluster) {
		dao.save(cluster);
	}

	public void updateCluster(Cluster cluster) {
		dao.update(cluster);
	}

	public Cluster getClusterById(long id) {
		return dao.queryById(id);
	}

	public Cluster getClusterByName(String name) {
		return dao.queryByName(name);
	}

	public List<Cluster> getClusterList() {
		return dao.queryAll();
	}

	public long totalNumber() {
		return dao.rowCount();
	}

	public List<Cluster> getByPage(int pageNum, int pageSize) {
		return dao.queryByPage(pageNum, pageSize);
	}
}
