/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-13 13:37
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.ClusterMeta;

public interface IClusterMetaService {
		public void addClusterMeta(ClusterMeta meta);
		
		public void updateClusterMeta(ClusterMeta meta);
		
		public ClusterMeta getClusterMetaById(long id);
		
		public ClusterMeta getClusterMetaByName(String name);
		
		public List<ClusterMeta> getClusterMetaList();
}