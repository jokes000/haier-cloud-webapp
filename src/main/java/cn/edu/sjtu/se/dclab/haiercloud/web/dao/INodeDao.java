/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 17:03
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Node;

public interface INodeDao {

	public void save(Node node);

	public void update(Node node);

	public Node queryById(long id);

	public Node queryByName(String name);

	public void delete(Node node);

	public void deleteById(long id);

	public List<Node> queryAll();
}