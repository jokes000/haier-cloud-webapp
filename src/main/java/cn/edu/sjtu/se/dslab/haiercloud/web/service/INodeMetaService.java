/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-13 19:57
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.NodeMeta;

public interface INodeMetaService {
    
    public void addNodeMeta(NodeMeta meta);

    public void updateNodeMeta(NodeMeta meta);

    public NodeMeta getNodeMetaById(long id);

    public NodeMeta getNodeMetaByName(String name);

    public List<NodeMeta> getNodeMetaList();
}
