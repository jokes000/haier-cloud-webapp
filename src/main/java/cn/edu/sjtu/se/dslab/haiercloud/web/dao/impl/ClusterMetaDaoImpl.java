/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 16:55
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IClusterMetaDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.ClusterMeta;

@Repository("clusterMetaDao")
public class ClusterMetaDaoImpl implements IClusterMetaDao {
    // constructor
    public ClusterMetaDaoImpl() {
    }

    // import baseDao
    @Resource(name = "baseDao")
    private IBaseDao<ClusterMeta> baseDao;

    public IBaseDao<ClusterMeta> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(IBaseDao<ClusterMeta> baseDao) {
        this.baseDao = baseDao;
    }

    // operations
    public void save(ClusterMeta meta) {
        baseDao.save(meta);
    }

    public void update(ClusterMeta meta) {
        baseDao.update(meta);
    }

    public ClusterMeta queryById(long id) {
        return (ClusterMeta) baseDao.queryById(ClusterMeta.class, id);
    }

    
    public ClusterMeta queryByName(String name) {
        return baseDao.queryByProperty(ClusterMeta.class, "name", name).get(0);
    }

    public void delete(ClusterMeta meta) {
        baseDao.delete(meta);
    }

    public void deleteById(long id) {
        baseDao.deleteById(ClusterMeta.class, id);
    }

    public List<ClusterMeta> queryAll() {
        return baseDao.queryAll(ClusterMeta.class);
    }
}