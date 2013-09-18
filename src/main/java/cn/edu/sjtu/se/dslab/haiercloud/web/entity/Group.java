package cn.edu.sjtu.se.dslab.haiercloud.web.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * group entity
 * 
 * @author smile
 * 
 */
@Entity
@Table(name = "TB_GROUP")
public class Group implements Serializable {

	private static final long serialVersionUID = -1018325173459129325L;

	/**
	 * Properties
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 30)
	private String name;
	@ManyToMany(targetEntity = cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "TB_GROUP_PERM", joinColumns = { @JoinColumn(name = "FK_GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "FK_PERM_ID") })
	private Set<Perm> permsList = new HashSet<Perm>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Perm> getPermsList() {
		return permsList;
	}

	public void setPermsList(Set<Perm> permsList) {
		this.permsList = permsList;
	}
}
