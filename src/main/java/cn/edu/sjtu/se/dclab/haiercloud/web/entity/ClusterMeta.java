/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 11:18
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_CLUSTERMETA")
public class ClusterMeta implements java.io.Serializable {

	private static final long serialVersionUID = -1309793158485818845L;
  
	// properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="name", nullable = false, length = 32)
	private String name;
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy="meta")
	private List<NodeMeta> nodes;

	// Constructor
	public ClusterMeta() {}

	public ClusterMeta(String name) {
	    super();
	    this.name = name;
	}
	// Constructor end

    // getters and setters
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

    public List<NodeMeta> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeMeta> nodes) {
        this.nodes = nodes;
    }
    // end of getters and setters

    // serializable implementation
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        ClusterMeta other = (ClusterMeta)obj;
        if (id != other.id)
            return false;
        if (name.equals(other.name))
            return false;

        return true;
    }
    // end of serializable implementation
}