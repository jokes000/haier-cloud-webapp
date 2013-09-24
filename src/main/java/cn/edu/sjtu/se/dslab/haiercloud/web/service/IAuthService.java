package cn.edu.sjtu.se.dslab.haiercloud.web.service;

import java.util.List;
import java.util.Set;

import cn.edu.sjtu.se.dslab.haiercloud.web.auth.CommonVariableModel;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Group;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.User;

public interface IAuthService {

	/* ------------------------- 登陆管理 ------------------------- */
	public boolean isAuthenticated();
	
	public boolean register(User user);
	
	public CommonVariableModel getCommonVariableModel();
	
	/* ------------------------- 用户管理 ------------------------- */
	public boolean updateUserPassword(String oldPassword, String newPassword);

	public User getUserById(long id);
	
	public List<User> getAllUsers();

	public List<User> getUserByPage(int pageNum, int pageSize);

	public void saveUser(User user);

	public void updateUser(User user);

	public boolean isUsernameUnique(String username);

	public void deleteUsers(List<Long> ids);

	public User getUserByUsername(String username);

	/* ------------------------- 权限管理 ------------------------- */
	public Perm getPermById(long id);

	public List<Perm> getPermByPage(int pageNum, int pageSize);

	public void savePerm(Perm perm);

	public void deletePerms(List<Long> ids);

	public List<Perm> getAllPerms();

	public Set<Perm> getUserPerms(long userid);

	/* ------------------------- 组管理 ------------------------- */
	public Group getGroup(long id);
	
	public void saveGroup(Group group);

	public List<Group> getAllGroups();

	public void deleteGroups(List<Long> ids);

	public List<Group> getGroupsByPage(int pageNum, int pageSize);

	public Set<Group> getUserGroups(long userid);

}
