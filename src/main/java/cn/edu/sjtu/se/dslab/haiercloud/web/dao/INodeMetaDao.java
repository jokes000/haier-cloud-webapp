/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 17:01
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.dao;

import java.util.List;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.NodeMeta;

public interface INodeMetaDao {

    public void save(NodeMeta meta);

    public void update(NodeMeta meta);

    public void delete(NodeMeta meta);

    public void deleteById(long id);

    public NodeMeta queryById(long id);
    
    public NodeMeta queryByName(String name);

    public List<NodeMeta> queryAll();
}