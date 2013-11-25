package cn.edu.sjtu.se.dclab.haiercloud.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.INodeMetaDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.NodeMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.INodeMetaService;

@Service("nodeMetaService")
@Transactional
public class NodeMetaServiceImpl implements INodeMetaService {

	// Properties
	@Resource(name = "nodeMetaDao")
	private INodeMetaDao dao;

	public void addNodeMeta(NodeMeta meta) {
		dao.save(meta);
	}

	public void updateNodeMeta(NodeMeta meta) {
		dao.update(meta);
	}

	public NodeMeta getNodeMetaById(long id) {
		return dao.queryById(id);
	}

	public NodeMeta getNodeMetaByName(String name) {
		return dao.queryByName(name);
	}

	public List<NodeMeta> getNodeMetaList() {
		return dao.queryAll();
	}
}
