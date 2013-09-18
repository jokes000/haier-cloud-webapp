/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 14:41
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;

public interface IClusterDao {

    public void save(Cluster cluster);

    public void update(Cluster cluster);
    
    public long rowCount();

    public Cluster queryById(long id);
    
    public Cluster queryByName(String name);

    public void delete(Cluster cluster);

    public void deleteById(long id);

    public List<Cluster> queryAll();
    
    public List<Cluster> queryByPage(int pageNum, int pageSize);
}