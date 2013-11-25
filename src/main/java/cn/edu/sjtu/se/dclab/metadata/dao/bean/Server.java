package cn.edu.sjtu.se.dclab.metadata.dao.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

@Entity
@Table(name = "SERVER")
public class Server implements Serializable{

	private Long id;
	private String s_name;
	private String type;
	private String host;
	private String port;
	private String username;
	private String password;
	private User user;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "S_NAME", nullable = false)
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	
	@Column(name = "TYPE", nullable = false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "HOST", nullable = false)
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	@Column(name = "PORT", nullable = false)
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
}
