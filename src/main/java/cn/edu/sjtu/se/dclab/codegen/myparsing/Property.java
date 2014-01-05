package cn.edu.sjtu.se.dclab.codegen.myparsing;

public class Property {
	private String type;
	private String name;
	private String column;

	public Property(){
		
	}
	
	public Property(String type, String name, String column) {
		super();
		this.type = type;
		this.name = name;
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpperName() {
		if (name != null && !name.equals("")) {
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		return name;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}
