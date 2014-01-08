package cn.edu.sjtu.se.dclab.haiercloud.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 资源安全类
 * 
 * @author smile
 * 
 */
@Entity
@Table(name = "TB_PERM")
public class Perm implements Serializable {

	private static final long serialVersionUID = -8136773190814529299L;

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	// action url
	@Column(length = 256)
	private String value;
	// 备注
	@Column(length = 512)
	private String remark;
	// shiro permission 字符串
	@Column(unique = true, length = 256)
	private String permission;

	/**
	 * 构造方法
	 */
	public Perm() {

	}

	/**
	 * 获取资源ID
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置资源ID
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取资源操作URL
	 * 
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置资源操作URL
	 * 
	 * @param value
	 *            资源操作URL
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取备注
	 * 
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取permission字符串
	 * 
	 * @return String
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 设置permission字符串
	 * 
	 * @param permission
	 *            字符串
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

}
