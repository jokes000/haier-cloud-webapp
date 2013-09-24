package cn.edu.sjtu.se.dslab.haiercloud.web.auth;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.Assert;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Group;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IAuthService;
import cn.edu.sjtu.se.dslab.haiercloud.web.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * apache shiro 的公用授权类
 * 
 * @author smile
 * 
 */
public abstract class AuthorizationRealm extends AuthorizingRealm {

	@Resource(name = "authService")
	IAuthService authService;

	private List<String> defaultPermissions = Lists.newArrayList();

	private List<String> defaultRoles = Lists.newArrayList();

	/**
	 * set default permissions
	 * 
	 * @param defaultPermissionString
	 *            若存在多个值，使用逗号","分割
	 */
	public void setDefaultPermissionString(String defaultPermissionString) {
		String[] perms = StringUtils.split(defaultPermissionString, ",");
		CollectionUtils.addAll(defaultPermissions, perms);
	}

	/**
	 * set default permissions
	 * 
	 * @param defaultPermission
	 */
	public void setDefaultPermission(List<String> defaultPermission) {
		this.defaultPermissions = defaultPermission;
	}

	/**
	 * 设置默认 role
	 * 
	 * @param defaultRoleString
	 */
	public void setDefaultRoleString(String defaultRoleString) {
		String[] roles = StringUtils.split(defaultRoleString, ",");
		CollectionUtils.addAll(defaultRoles, roles);
	}

	/**
	 * 设置默认role
	 * 
	 * @param defaultRole
	 */
	public void setDefaultRole(List<String> defaultRole) {
		this.defaultRoles = defaultRole;
	}

	/**
	 * 
	 * 当用户进行访问链接时的授权方法
	 * 
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		CommonVariableModel model = (CommonVariableModel) principals
				.getPrimaryPrincipal();

		Assert.notNull(model, "找不到pincipals中的CommonVariableModel");

		long id = model.getUser().getId();

		// 加载用户的组信息和资源信息
		Set<Perm> authorizationInfo = authService.getUserPerms(id);
		Set<Group> groupsList = authService.getUserGroups(id);

		model.setAuthorizationInfo(authorizationInfo);
		model.setGroupsList(groupsList);

		// 添加用户拥有的permission
		addPermissions(info, authorizationInfo);
		// 添加用户拥有的role
		addRoles(info, groupsList);

		return info;
	}

	private void addRoles(SimpleAuthorizationInfo info, Set<Group> groupsList) {
		
		/**
		 *  目前addRoles没有起到作用
		 */
		List<String> temp = CollectionUtils.extractToList(groupsList, "role",
				true);
		List<String> roles = getValue(temp, "roles\\[(.*?)\\]");

		if (CollectionUtils.isNotEmpty(defaultRoles)) {
			CollectionUtils.addAll(roles, defaultRoles.iterator());
		}

		info.addRoles(roles);

		

		// 将group对应的permission加入
		for (Group group : groupsList) {
			
			List<String> tmp = CollectionUtils.extractToList(group.getPermsList(), "permission",
					true);
			List<String> permissions = getValue(tmp, "perms\\[(.*?)\\]");
			// 添加默认的permissions到permissions
			if (CollectionUtils.isNotEmpty(defaultPermissions)) {
				CollectionUtils.addAll(permissions, defaultPermissions.iterator());
			}

			// 将当前用户拥有的permissions设置到SimpleAuthorizationInfo中
			info.addStringPermissions(permissions);	
		}
	}

	private void addPermissions(SimpleAuthorizationInfo info,
			Set<Perm> authorizationInfo) {
		// 解析当前用户资源中的permissions
		List<String> temp = CollectionUtils.extractToList(authorizationInfo,
				"permission", true);

		List<String> permissions = getValue(temp, "perms\\[(.*?)\\]");

		// 添加默认的permissions到permissions
		if (CollectionUtils.isNotEmpty(defaultPermissions)) {
			CollectionUtils.addAll(permissions, defaultPermissions.iterator());
		}

		// 将当前用户拥有的permissions设置到SimpleAuthorizationInfo中
		info.addStringPermissions(permissions);
	}

	private List<String> getValue(List<String> obj, String regex) {
		List<String> result = new ArrayList<String>();

		if (CollectionUtils.isEmpty(obj)) {
			return result;
		}

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(StringUtils.join(obj, ","));

		while (matcher.find()) {
			result.add(matcher.group(1));
		}

		return result;
	}
}
