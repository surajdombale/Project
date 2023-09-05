package com.shopme.entity;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_name", length = 40, nullable = false, unique = true)
	private String name;

	@Column(name = "alias", length = 40, nullable = false, unique = true)
	private String alias;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Parent_id", referencedColumnName = "id")
	List<SubCategory> subcategory = new ArrayList<SubCategory>();

	public Category() {
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

	public Category(String name, String alias, List<SubCategory> subcategory) {
		super();
		this.name = name;
		this.alias = alias;
		this.subcategory = subcategory;
	}

	public Category(Integer id, String name, String alias, List<SubCategory> subcategory) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.subcategory = subcategory;
	}

	public Category(Integer id, List<SubCategory> subcategory) {
		super();
		this.id = id;
		this.subcategory = subcategory;
	}

	public Category(String name, String alias) {
		super();
		this.name = name;
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<SubCategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<SubCategory> subcategory) {
		this.subcategory = subcategory;
	}

}
