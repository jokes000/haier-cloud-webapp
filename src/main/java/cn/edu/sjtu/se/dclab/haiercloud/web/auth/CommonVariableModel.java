package cn.edu.sjtu.se.dclab.haiercloud.web.auth;

import java.io.Serializable;
import java.util.Set;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Group;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Perm;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

/**
 * 系统常用变量实体
 * 
 * @author smile
 * 
 */
public class CommonVariableModel implements Serializable {

	private static final long serialVersionUID = 250572475194465329L;

	/**
	 * 属性
	 */
	private User user;
	private Set<Group> groupsList;
	private Set<Perm> authorizationInfo;
	private Set<Perm> menusList;

	/**
	 * 构造函数
	 */
	public CommonVariableModel() {
	}

	public CommonVariableModel(User user) {
		this.user = user;
	}

	public CommonVariableModel(User user, Set<Group> groupsList,
			Set<Perm> authorizationInfo, Set<Perm> menusList) {
		this.user = user;
		this.setGroupsList(groupsList);
		this.setAuthorizationInfo(authorizationInfo);
		this.setMenusList(menusList);
	}

	/**
	 * 获取当前用户
	 * 
	 * @return {@link User}
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置当前用户
	 * 
	 * @return String
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return user.getUsername();
	}

	public Set<Group> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(Set<Group> groupsList) {
		this.groupsList = groupsList;
	}

	public Set<Perm> getAuthorizationInfo() {
		return authorizationInfo;
	}

	public void setAuthorizationInfo(Set<Perm> authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}

	public Set<Perm> getMenusList() {
		return menusList;
	}

	public void setMenusList(Set<Perm> menusList) {
		this.menusList = menusList;
	}

}
