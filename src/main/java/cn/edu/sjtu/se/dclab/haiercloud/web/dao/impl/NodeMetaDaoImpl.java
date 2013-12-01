/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 17:00
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.dao.INodeMetaDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Node;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.NodeMeta;

@Repository("nodeMetaDao")
public class NodeMetaDaoImpl implements INodeMetaDao {
    // constructor
    public NodeMetaDaoImpl() {
    }

    // import baseDao
    @Resource(name = "baseDao")
    private IBaseDao<NodeMeta> baseDao;

    public IBaseDao<NodeMeta> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(IBaseDao<NodeMeta> baseDao) {
        this.baseDao = baseDao;
    }

    // operations
    public void save(NodeMeta meta) {
        baseDao.save(meta);
    }

    public void update(NodeMeta meta) {
        baseDao.update(meta);
    }

    public NodeMeta queryById(long id) {
        return (NodeMeta) baseDao.queryById(NodeMeta.class, id);
    }

    
    public NodeMeta queryByName(String name) {
        return baseDao.queryByProperty(NodeMeta.class, "name", name).get(0);
    }

    public void delete(NodeMeta meta) {
        baseDao.delete(meta);
    }

    public void deleteById(long id) {
        baseDao.deleteById(NodeMeta.class, id);
    }

    public List<NodeMeta> queryAll() {
        return (List<NodeMeta>) baseDao.queryAll(NodeMeta.class);
    }
}