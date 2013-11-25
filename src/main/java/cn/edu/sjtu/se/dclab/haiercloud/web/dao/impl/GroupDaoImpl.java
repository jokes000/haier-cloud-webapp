package cn.edu.sjtu.se.dclab.haiercloud.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IGroupDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Group;

@Repository("groupDao")
public class GroupDaoImpl implements IGroupDao {

	/**
	 * Properties
	 */
	@Resource(name = "baseDao")
	private IBaseDao<Group> baseDao;
	
	
	public void save(Group group) {
		baseDao.save(group);
	}

	
	public void update(Group group) {
		baseDao.update(group);
	}

	
	public long rowCount() {
		return baseDao.rowCount(Group.class);
	}

	
	public Group queryById(long id) {
		return baseDao.queryById(Group.class, id);
	}

	
	public void delete(Group group) {
		baseDao.delete(group);
	}

	
	public void deleteById(long id) {
		baseDao.deleteById(Group.class, id);
	}

	
	public List<Group> queryAll() {
		return (List<Group>)baseDao.queryAll(Group.class);
	}

	
	public List<Group> queryByPage(int pageNum, int pageSize) {
		return (List<Group>)baseDao.queryByPage(pageNum, pageSize, Group.class);
	}

}
