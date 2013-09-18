package cn.edu.sjtu.se.dslab.haiercloud.web.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.haiercloud.web.auth.CommonVariableModel;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IGroupDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IPermDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IUserDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Group;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dslab.haiercloud.web.exception.ServiceException;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IAuthService;

/**
 * 帐户管理业务逻辑
 * 
 * @author smile
 * 
 */
@Service("authService")
@Transactional
public class AuthServiceImpl implements IAuthService {

	@Resource(name = "userDao")
	private IUserDao userDao;
	@Resource(name = "permDao")
	private IPermDao permDao;
	@Resource(name = "groupDao")
	private IGroupDao groupDao;

	/**
	 * shiro 授权缓存key
	 */
	private final String ShiroAuthorizationCache = "shiroAuthorizationCache";

	/**
	 * 更新当前用户密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 */
	public boolean updateUserPassword(String oldPassword, String newPassword) {
		Subject subject = SecurityUtils.getSubject();
		User user = null;
		if (subject != null && subject.getPrincipal() != null && subject.getPrincipal() instanceof CommonVariableModel ) {
			user = ((CommonVariableModel)subject.getPrincipal()).getUser();
		}		
		
		oldPassword = new SimpleHash("MD5", oldPassword.toCharArray()).toString();
		if (user.getPassword().equals(oldPassword)) {
			String temp = new SimpleHash("MD5", newPassword).toHex();
			user.setPassword(temp);
			userDao.update(user);
			return true;
		}
		return false;
	}

	public User getUserById(long id) {
		return userDao.queryById(id);
	}

	public List<User> getUserByPage(int pageNum, int pageSize) {
		return userDao.queryByPage(pageNum, pageSize);
	}

	public void saveUser(User user) {
		if (!isUsernameUnique(user.getUsername())) {
			throw new ServiceException("用户名已存在");
		}
		
		String password = new SimpleHash("MD5", user.getPassword()).toHex();
		user.setPassword(password);
		userDao.save(user);
	}

	@CacheEvict(value = ShiroAuthorizationCache, allEntries = true)
	public void updateUser(User user) {
		userDao.update(user);
	}

	public boolean isUsernameUnique(String username) {
		return userDao.queryByUserName(username) == null;
	}

	public void deleteUsers(List<Long> ids) {
		for (long id : ids) {
			userDao.deleteById(id);
		}
	}

	public User getUserByUsername(String username) {
		return userDao.queryByUserName(username);
	}

	public Perm getPermById(long id) {
		return permDao.queryById(id);
	}

	public List<Perm> getPermByPage(int pageNum, int pageSize) {
		return permDao.queryByPage(pageNum, pageSize);
	}

	public void savePerm(Perm perm) {
		permDao.save(perm);
	}

	public void deletePerms(List<Long> ids) {
		for (long id : ids) {
			permDao.deleteById(id);
		}
	}

	public List<Perm> getAllPerms() {
		return permDao.queryAll();
	}

	public Set<Perm> getUserPerms(long userid) {
		return userDao.queryById(userid).getPermsList();
	}

	public Group getGroup(long id) {
		return groupDao.queryById(id);
	}

	public List<Group> getAllGroups() {
		return groupDao.queryAll();
	}
	
	@CacheEvict(value=ShiroAuthorizationCache,allEntries=true)
	public void saveGroup(Group group) {
		groupDao.save(group);
	}

	public void deleteGroups(List<Long> ids) {
		for (long id : ids) {
			groupDao.deleteById(id);
		}
	}

	public List<Group> getGroupsByPage(int pageNum, int pageSize) {
		return groupDao.queryByPage(pageNum, pageSize);
	}

	public Set<Group> getUserGroups(long userid) {
		return userDao.queryById(userid).getGroupsList();
	}

}
