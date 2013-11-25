package cn.edu.sjtu.se.dclab.haiercloud.web.auth;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * UsernamePasswordToke扩展，添加一个rememberMeValue字段，获取提交上来的rememberMe值
 * 根据该rememberMe值去设置Cookie的有效时间。
 * 
 * @author vincent
 * 
 */
@SuppressWarnings("serial")
public class UsernamePasswordTokenExtend extends UsernamePasswordToken {

	// rememberMe cookie的有效时间
	private Integer rememberMeCookieValue;

	public UsernamePasswordTokenExtend() {

	}

	public UsernamePasswordTokenExtend(String username, String password,
	        boolean rememberMe, String host, Integer rememberMeCookieValue) {
		super(username, password, rememberMe, host);
		this.rememberMeCookieValue = rememberMeCookieValue;
	}

	/**
	 * 获取rememberMe cookie的有效时间
	 * 
	 * @return Integer
	 */
	public Integer getRememberMeCookieValue() {
		return rememberMeCookieValue;
	}

	/**
	 * 设置rememberMe cookie的有效时间
	 * 
	 * @param rememberMeCookieValue
	 *            cookie的有效时间
	 */
	public void setRememberMeCookieValue(Integer rememberMeCookieValue) {
		this.rememberMeCookieValue = rememberMeCookieValue;
	}

}
