package com.shopme.model;


public class SubCategory {
public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
private Integer id;
private byte[] image;
private String name;
private String alias;
private boolean enabled;
public String parentName;
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}


public byte[] getImage() {
	return image;
}

public void setImage(byte[] image) {
	this.image = image;
}

public String getAlias() {
	return alias;
}
public void setAlias(String alias) {
	this.alias = alias;
}

public SubCategory(Integer id, String name, String alias, boolean enabled,String parentName) {
	super();
	this.parentName=parentName;
	this.id = id;
	this.name = name;
	this.alias = alias;
	this.enabled = enabled;
}
public SubCategory(byte[] image, String name, String alias, boolean enabled) {
	super();
	this.image = image;
	this.name = name;
	this.alias = alias;
	this.enabled = enabled;
}
public SubCategory(Integer id, byte[] image, String name, String alias, boolean enabled, String parentName) {
	super();
	this.id = id;
	this.image = image;
	this.name = name;
	this.alias = alias;
	this.enabled = enabled;
	this.parentName = parentName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
