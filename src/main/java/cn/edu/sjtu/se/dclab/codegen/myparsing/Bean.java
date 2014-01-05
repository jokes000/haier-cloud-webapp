package cn.edu.sjtu.se.dclab.codegen.myparsing;

import java.util.List;

public class Bean {
	private String packageName;
	private String className;
	private String tableName;

	private List<Property> properties;
	private List<Association> associations;
	private List<Operation> operations;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getLowerClassName() {
		if (className != null && !className.equals("")) {
			return className.substring(0, 1).toLowerCase() + className.substring(1);
		}
		return className;
	}
	
	public String getUpperClassName() {
		if (className != null && !className.equals("")) {
			return className.substring(0, 1).toUpperCase() + className.substring(1);
		}
		return className;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public List<Association> getAssociations() {
		return associations;
	}

	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
}

