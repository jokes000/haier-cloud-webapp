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
@Table(name = "TABLE_META")
public class TableMeta implements Serializable{

		private Long id;
		private String t_name;
		private User user;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		@Column(name = "T_NAME", nullable = false)
		public String getT_name() {
			return t_name;
		}
		public void setT_name(String t_name) {
			this.t_name = t_name;
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
