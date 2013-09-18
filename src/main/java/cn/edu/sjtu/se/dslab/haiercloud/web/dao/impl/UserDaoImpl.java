package cn.edu.sjtu.se.dslab.haiercloud.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IUserDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.User;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	// import baseDao
	@Resource(name = "baseDao")
	private IBaseDao<User> baseDao;

	
	public void save(User user) {
		baseDao.save(user);
	}

	
	public void update(User user) {
		baseDao.update(user);
	}

	
	public long rowCount() {
		return baseDao.rowCount(User.class);
	}

	
	public User queryById(long id) {
		return (User) baseDao.queryById(User.class, id);
	}

	
	public User queryByUserName(String username) {
		return baseDao.queryByProperty(User.class, "username", username).get(0);
	}

	
	public void delete(User user) {
		baseDao.delete(user);
	}

	
	public void deleteById(long id) {
		baseDao.deleteById(User.class, id);
	}

	
	public List<User> queryAll() {
		return (List<User>)baseDao.queryAll(User.class);
	}

	
	public List<User> queryByPage(int pageNum, int pageSize) {
		return (List<User>)baseDao.queryByPage(pageNum, pageSize, User.class);
	}

}
