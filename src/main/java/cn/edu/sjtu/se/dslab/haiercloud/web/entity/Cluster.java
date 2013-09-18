/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 13:25
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.entity;

import java.util.Set;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TB_CLUSTER")
public class Cluster implements java.io.Serializable {

    private static final long serialVersionUID = -2098803523149630750L;

    // Constructor
    public Cluster() {
    }

    /* properties */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "status", nullable = false)
    private int status; // 0: stable  1: deploying 2: error
    @ManyToOne
    @JoinColumn(name = "meta_id")
    private ClusterMeta meta;
    @Fetch( FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "cluster", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private Set<VirtualMachine> vms;
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

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ClusterMeta getMeta() {
        return meta;
    }

    public void setMeta(ClusterMeta meta) {
        this.meta = meta;
    }
    
    public Set<VirtualMachine> getVms() {
		return vms;
	}

	public void setVms(Set<VirtualMachine> vms) {
		this.vms = vms;
	}
    /* end of getter and setters */

    /* Serializable interfaces */
    /* end of Serializable interfaces */
}