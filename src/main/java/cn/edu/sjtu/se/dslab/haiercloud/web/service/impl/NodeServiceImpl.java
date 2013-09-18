package cn.edu.sjtu.se.dslab.haiercloud.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.INodeDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.INodeService;

@Service("nodeService")
@Transactional
public class NodeServiceImpl implements INodeService {

	// Properties
	@Resource(name = "nodeDao")
	INodeDao dao;

	// Operations
	public void addNode(Node node) {
		dao.save(node);
	}

	public void updateNode(Node node) {
		dao.update(node);
	}

	public Node getNodeById(long id) {
		return dao.queryById(id);
	}

	public Node getNodeByName(String name) {
		return dao.queryByName(name);
	}

	public List<Node> getNodeList() {
		return dao.queryAll();
	}

}
