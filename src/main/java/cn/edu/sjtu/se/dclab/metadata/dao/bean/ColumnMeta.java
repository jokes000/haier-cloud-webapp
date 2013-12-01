package cn.edu.sjtu.se.dclab.metadata.dao.bean;

import java.io.Serializable;

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
@Table(name = "COLUMN_META")
public class ColumnMeta implements Serializable{

	private Long id;
	private String c_name;
	private TableMeta tablemeta;
	private String type;
	private String length;
	private String cascade;
	private boolean notnull;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "C_NAME", nullable = false)
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	@ManyToOne
	@JoinColumn(name = "TABLEMETA_ID")
	public TableMeta getTablemeta() {
		return tablemeta;
	}
	public void setTablemeta(TableMeta tablemeta) {
		this.tablemeta = tablemeta;
	}
	
	@Column(name = "TYPE", nullable = false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "LENGTH")
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	
	@Column(name = "CASCADE_")
	public String getCascade() {
		return cascade;
	}
	public void setCascade(String cascade) {
		this.cascade = cascade;
	}
	
	@Column(name = "NOTNULL")
	public boolean isNotnull() {
		return notnull;
	}
	public void setNotnull(boolean notnull) {
		this.notnull = notnull;
	}


}
