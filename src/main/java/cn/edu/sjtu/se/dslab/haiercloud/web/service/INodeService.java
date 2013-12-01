package cn.edu.sjtu.se.dslab.haiercloud.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;

public interface INodeService {
	public void addNode(Node node);
	
	public void deleteNode(Node node);

	public void updateNode(Node node);

	public Node getNodeById(long id);

	public Node getNodeByName(String name);

	public List<Node> getNodeList();
}
