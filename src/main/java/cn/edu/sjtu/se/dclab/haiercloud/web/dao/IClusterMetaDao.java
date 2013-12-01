/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 16:53
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.ClusterMeta;

public interface IClusterMetaDao {

    public void save(ClusterMeta meta);

    public void update(ClusterMeta meta);

    public ClusterMeta queryById(long id);

    public ClusterMeta queryByName(String name);

    public void delete(ClusterMeta meta);

    public void deleteById(long id);

    public List<ClusterMeta> queryAll();
}