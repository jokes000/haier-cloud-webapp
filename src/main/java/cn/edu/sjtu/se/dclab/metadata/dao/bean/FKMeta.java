package cn.edu.sjtu.se.dclab.metadata.dao.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FK_META")
public class FKMeta implements Serializable {

	private Long id;
	private TableMeta tablemeta;
	private ColumnMeta columnmeta_fk;
	private TableMeta tablemeta_fk;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "TABLEMETA_ID")
	public TableMeta getTablemeta() {
		return tablemeta;
	}

	public void setTablemeta(TableMeta tablemeta) {
		this.tablemeta = tablemeta;
	}

	@OneToOne
	@JoinColumn(name = "COLUMNMETA_ID")
	public ColumnMeta getColumnmeta_fk() {
		return columnmeta_fk;
	}

	public void setColumnmeta_fk(ColumnMeta columnmeta_fk) {
		this.columnmeta_fk = columnmeta_fk;
	}

	@OneToOne
	@JoinColumn(name = "TABLEMETA_FK_ID")
	public TableMeta getTablemeta_fk() {
		return tablemeta_fk;
	}

	public void setTablemeta_fk(TableMeta tablemeta_fk) {
		this.tablemeta_fk = tablemeta_fk;
	}

}
