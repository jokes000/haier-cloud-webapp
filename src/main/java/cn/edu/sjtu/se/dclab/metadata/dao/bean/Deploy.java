package cn.edu.sjtu.se.dclab.metadata.dao.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

@Entity
@Table(name = "DEPLOY")
public class Deploy implements Serializable{
	
	private Long id;
	private User user;
	private DatabaseMeta databaseMeta;
	private Server server;
	private String status;
	private String msg;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "DATABASEMETA_ID")
	public DatabaseMeta getDatabaseMeta() {
		return databaseMeta;
	}
	public void setDatabaseMeta(DatabaseMeta databaseMeta) {
		this.databaseMeta = databaseMeta;
	}
	
	@ManyToOne
	@JoinColumn(name = "SERVER_ID")
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	
	@Column(name = "STATUS", nullable = false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "MSG", columnDefinition = "TEXT", length = 65535)
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
