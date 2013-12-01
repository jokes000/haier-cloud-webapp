/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 14:43
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IClusterDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Cluster;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.ClusterMeta;

@Repository("clusterDao")
public class ClusterDaoImpl implements IClusterDao {
    // constructor
    public ClusterDaoImpl() {
    }

    // import baseDao
    @Resource(name = "baseDao")
    private IBaseDao<Cluster> baseDao;

    public IBaseDao<Cluster> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(IBaseDao<Cluster> baseDao) {
        this.baseDao = baseDao;
    }

    // operations
    public void save(Cluster cluster) {
        baseDao.save(cluster);
    }

    public void update(Cluster cluster) {
        baseDao.update(cluster);
    }

    public long rowCount() {
        return baseDao.rowCount(Cluster.class);
    }
    
    public Cluster queryById(long id) {
        return (Cluster) baseDao.queryById(Cluster.class, id);
    }

    
    public Cluster queryByName(String name) {
        return baseDao.queryByProperty(Cluster.class, "name", name).get(0);
    }

    public void delete(Cluster cluster) {
        baseDao.delete(cluster);
    }

    public void deleteById(long id) {
        baseDao.deleteById(Cluster.class, id);
    }

    public List<Cluster> queryAll() {
        return (List<Cluster>) baseDao.queryAll(Cluster.class);
    }

    
    public List<Cluster> queryByPage(int pageNum, int pageSize) {
        return baseDao.queryByPage(pageNum, pageSize, Cluster.class);
    }
}