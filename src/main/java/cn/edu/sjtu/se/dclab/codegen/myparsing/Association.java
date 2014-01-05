package cn.edu.sjtu.se.dclab.codegen.myparsing;

public class Association {
	private String name;
	private String type;
	private String joinColumn;
	private String cascadeType;

	public Association(){
		
	}
	
	public Association(String name, Bean bean, String type, String joinColumn) {
		super();
		this.name = name;
		this.type = type;
		this.joinColumn = joinColumn;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public String getCascadeType() {
		return cascadeType;
	}

	public void setCascadeType(String cascadeType) {
		this.cascadeType = cascadeType;
	}
}

