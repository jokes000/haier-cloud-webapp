/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-13 19:57
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Cluster;

public interface IClusterService {
		public void addCluster(Cluster meta);
		
		public void updateCluster(Cluster meta);
		
		public Cluster getClusterById(long id);
		
		public Cluster getClusterByName(String name);
		
		public List<Cluster> getClusterList();
		
		public long totalNumber();
		
		public List<Cluster> getByPage(int pageNum, int pageSize);
}