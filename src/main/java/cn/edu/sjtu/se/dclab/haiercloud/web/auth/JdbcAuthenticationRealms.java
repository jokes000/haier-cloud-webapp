package cn.edu.sjtu.se.dclab.haiercloud.web.auth;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IAuthService;

public class JdbcAuthenticationRealms extends AuthorizationRealm {

	@Resource(name = "authService")
	IAuthService authService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		if (username == null || username.equals("")) {
			throw new AccountException("用户名不能为空");
		}

		User user = authService.getUserByUsername(username);
		if (user == null) {
			throw new UnknownAccountException("用户不存在");
		}

		CommonVariableModel model = new CommonVariableModel(user);

		return new SimpleAuthenticationInfo(model, user.getPassword(),
				getName());

	}

}
