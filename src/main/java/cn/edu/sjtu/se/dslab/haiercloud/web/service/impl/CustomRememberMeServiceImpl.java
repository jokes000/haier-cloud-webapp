package cn.edu.sjtu.se.dslab.haiercloud.web.service.impl;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.stereotype.Component;

import cn.edu.sjtu.se.dslab.haiercloud.web.auth.UsernamePasswordTokenExtend;

/**
 * self defined remember me service, set cookie's maxAge by page's input
 * 
 * @author smile
 *
 */
@Component("customRememberMeService")
public class CustomRememberMeServiceImpl extends CookieRememberMeManager{

	/**
	 * 构造方法，不在这里对Cookie的maxAge设置值
	 */
	public CustomRememberMeServiceImpl() {
		Cookie cookie = new SimpleCookie(DEFAULT_REMEMBER_ME_COOKIE_NAME);
        cookie.setHttpOnly(true);
        setCookie(cookie);
	}
	
	/**
	 * 重写父类方法，写入Cookie时，先把传过来的有效时间设置到cookie里面在序列化Identity
	 */
	public void rememberIdentity(Subject subject, AuthenticationToken token,AuthenticationInfo authcInfo) {
		UsernamePasswordTokenExtend tokeExtend = (UsernamePasswordTokenExtend) token;
		
		getCookie().setMaxAge(tokeExtend.getRememberMeCookieValue());
		
		super.rememberIdentity(subject, token, authcInfo);
	}
}
