package cn.edu.sjtu.se.dslab.haiercloud.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IClusterMetaDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.ClusterMeta;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IClusterMetaService;

@Service("clusterMetaService")
@Transactional
public class ClusterMetaServiceImpl implements IClusterMetaService {

	// Properties
	@Resource(name = "clusterMetaDao")
	private IClusterMetaDao dao;

	// implemented methods
	public void addClusterMeta(ClusterMeta meta) {
		dao.save(meta);
	}

	public void updateClusterMeta(ClusterMeta meta) {
		dao.update(meta);
	}

	public ClusterMeta getClusterMetaById(long id) {
		return dao.queryById(id);
	}

	public ClusterMeta getClusterMetaByName(String name) {
		return dao.queryByName(name);
	}

	public List<ClusterMeta> getClusterMetaList() {
		return dao.queryAll();
	}

}
