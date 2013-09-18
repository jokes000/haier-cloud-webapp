/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 17:03
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.INodeDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.ClusterMeta;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Node;

@Repository("nodeDao")
public class NodeDaoImpl implements INodeDao {
    // constructor
    public NodeDaoImpl() {
    }

    // import baseDao
    @Resource(name = "baseDao")
    private IBaseDao<Node> baseDao;

    public IBaseDao<Node> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(IBaseDao<Node> baseDao) {
        this.baseDao = baseDao;
    }

    // operations
    public void save(Node node) {
        baseDao.save(node);
    }

    public void update(Node node) {
        baseDao.update(node);
    }

    public Node queryById(long id) {
        return (Node) baseDao.queryById(Node.class, id);
    }

    
    public Node queryByName(String name) {
        return baseDao.queryByProperty(Node.class, "name", name).get(0);
    }

    public void delete(Node node) {
        baseDao.delete(node);
    }

    public void deleteById(long id) {
        baseDao.deleteById(Node.class, id);
    }

    public List<Node> queryAll() {
        return (List<Node>) baseDao.queryAll(Node.class);
    }
}