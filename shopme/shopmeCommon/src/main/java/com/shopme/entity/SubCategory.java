package com.shopme.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "subcategory")
public class SubCategory {
	public SubCategory(Integer id, byte[] image, String name, String alias, String parentName, boolean enabled) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.alias = alias;
		this.parentName = parentName;
		this.enabled = enabled;
	}

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Lob
	@Column(length = 52428800)
	private byte[] image;

	@Column(name = "subCategory_name", length = 40, nullable = false, unique = true)
	private String name;
	@Column(name = "subCategory_alias", length = 40, nullable = false, unique = true)
	private String alias;
	@Column
	public String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column
	private boolean enabled;

	public SubCategory(String name, byte[] image, String alias, boolean enabled, String parentName) {
		super();
		this.parentName = parentName;
		this.name = name;
		this.alias = alias;
		this.enabled = enabled;
		this.image = image;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public SubCategory() {
	}

	public byte[] getImage() {
		return image;
	}

	public void setImageData(byte[] image) {
		this.image = image;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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
