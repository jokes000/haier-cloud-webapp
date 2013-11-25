package cn.edu.sjtu.se.dclab.metadata.dao.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RELATION")
public class Relation implements Serializable{
	private Long id;
	private DatabaseMeta databaseMeta;
	private TableMeta tableMeta;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "DATABASEMETA_ID")
	public DatabaseMeta getDatabaseMeta() {
		return databaseMeta;
	}
	public void setDatabaseMeta(DatabaseMeta databaseMeta) {
		this.databaseMeta = databaseMeta;
	}
	
	@ManyToOne
	@JoinColumn(name = "TABLEMETA_ID")
	public TableMeta getTableMeta() {
		return tableMeta;
	}
	public void setTableMeta(TableMeta tableMeta) {
		this.tableMeta = tableMeta;
	}
}
