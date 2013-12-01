/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 13:39
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.entity;

// persistence class
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



// utils
import java.util.Set;

@Entity
@Table(name = "TB_VM")
public class VirtualMachine implements java.io.Serializable {

	public static final int ERROR = 0;
	public static final int STABLE = 1;
	public static final int DEPLOY = 2;
	public static final int DELETE = 3;
    private static final long serialVersionUID = -2501856944883399082L;

    // Constructor
    public VirtualMachine() {
    }

    /* properties */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false, length = 155)
    private String name;
    @Column(name = "cpu", nullable = false, length = 155)
    private String cpu;
    @Column(name = "memory", nullable = false)
    private int memory;
    @Column(name = "ip", nullable = false, length = 16)
    private String ip;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name = "MAC", nullable = false, length = 20)
    private String mac;
    @Column(name = "storage", nullable = false)
    private int storage;
    @Column(name = "os", nullable = false)
    private String os;
    @Column(name = "board_width", nullable = true)
    private int boardWidth;
    @OneToMany(mappedBy = "vm", cascade = { CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @Fetch( FetchMode.SUBSELECT)
    private Set<Node> nodes;
    @ManyToOne
    @JoinColumn(name = "cluster_id")
    private Cluster cluster;
    @Column(name = "status", nullable = false)
    private int status;
    
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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
    /* end of getter and setters */

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

    /* Serializable interfaces */
    /* end of Serializable interfaces */
}