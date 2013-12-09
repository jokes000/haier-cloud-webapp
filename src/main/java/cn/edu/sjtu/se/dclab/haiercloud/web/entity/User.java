package cn.edu.sjtu.se.dclab.haiercloud.web.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * user class
 * 
 * @author smile
 * 
 */

@Entity
@Table(name = "TB_USER")
@NamedQuery(name = User.UpdatePassword, query = "update User u set u.password = ?1 where u.id = ?2")
public class User implements Serializable {

	private static final long serialVersionUID = 1292424748818673960L;

	/**
	 * update user password query
	 */
	public static final String UpdatePassword = "updatePassword";

	// Properties
	// user id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	// login name
	@Column(length = 32, unique = true, nullable = false, updatable = false)
	private String username;
	// login password
	@Column(nullable = false, length = 60, updatable = true)
	private String password;
	// email
	@Column(length = 128)
	private String email;
	// groups
	@Fetch( FetchMode.SUBSELECT)
	@ManyToMany(targetEntity = cn.edu.sjtu.se.dclab.haiercloud.web.entity.Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "TB_GROUP_USER", joinColumns = { @JoinColumn(name = "FK_USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "FK_GROUP_ID") })
	private Set<Group> groupsList = new HashSet<Group>();
	// permissions and urls user can access
	@Fetch( FetchMode.SUBSELECT)
	@ManyToMany(targetEntity = cn.edu.sjtu.se.dclab.haiercloud.web.entity.Perm.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "TB_PERM_USER", joinColumns = { @JoinColumn(name = "FK_USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "FK_PERM_ID") })
	private Set<Perm> permsList = new HashSet<Perm>();

	/**
	 * Constructor
	 */
	public User() {
	}

	/**
	 * retrieve user id
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * set user id
	 * 
	 * @param id
	 *            user id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get login name
	 * 
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set login name
	 * 
	 * @param username
	 *            login name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * retrieve user password
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set login password
	 * 
	 * @param password
	 *            login password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * get email
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set email
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Group> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(Set<Group> groupsList) {
		this.groupsList = groupsList;
	}

	public Set<Perm> getPermsList() {
		return permsList;
	}

	public void setPermsList(Set<Perm> permsList) {
		this.permsList = permsList;
	}
	
}
