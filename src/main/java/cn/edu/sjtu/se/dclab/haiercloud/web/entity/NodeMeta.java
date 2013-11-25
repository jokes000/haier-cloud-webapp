/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 12:53
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_NODEMETA")
public class NodeMeta implements java.io.Serializable {

    private static final long serialVersionUID = -5384179243571585874L;

    // Constructor
    public NodeMeta() {
    }

    /* properties */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false, length = 32)
    private String name;
    @Column(name = "script", nullable = true, length = 255)
    private String script;
    @ManyToOne
    @JoinColumn(name = "meta_id")
    private ClusterMeta meta;
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "meta")
    private List<Node> nodes;

    /* end of properties */

    /* getter and setter */
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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    /* end of getter and setter */
}