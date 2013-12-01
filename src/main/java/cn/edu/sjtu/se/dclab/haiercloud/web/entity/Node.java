/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 13:25
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_NODE")
public class Node implements java.io.Serializable {

	private static final long serialVersionUID = 5185272712366834262L;

	// Constructor
	public Node() {
	}

	public Node(String name, int port, NodeMeta meta, VirtualMachine vm) {
		this.name = name;
		this.port = port;
		this.meta = meta;
		this.vm = vm;
	}

	/* properties */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "port", nullable = false)
	private int port;
	@Column(name = "parent",length = 255)
	private String parent;
	@ManyToOne
	@JoinColumn(name = "meta_id")
	private NodeMeta meta;
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name = "vm_id")
	private VirtualMachine vm;

	/* end of properties */

	/* getter and setters */
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public NodeMeta getMeta() {
		return meta;
	}

	public void setMeta(NodeMeta meta) {
		this.meta = meta;
	}
	
	public VirtualMachine getVm() {
		return vm;
	}

	public void setVm(VirtualMachine vm) {
		this.vm = vm;
	}
	/* end of getter and setters */
	/* Serializable interfaces */
	/* end of Serializable interfaces */
}