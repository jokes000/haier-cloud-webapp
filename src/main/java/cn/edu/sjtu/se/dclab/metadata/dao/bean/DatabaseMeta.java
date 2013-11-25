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
@Table(name = "DATABASE_META")
public class DatabaseMeta implements Serializable{

	private Long id;
	private String db_name;
	private User user;
	private Server server;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "DB_NAME", nullable = false)
	public String getDb_name() {
		return db_name;
	}
	public void setDb_name(String db_name) {
		this.db_name = db_name;
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
	@JoinColumn(name = "SERVER_ID")
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	
}
