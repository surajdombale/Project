package com.shopme.model;

import java.util.ArrayList;
import java.util.List;

import com.shopme.entity.SubCategory;

public class Category {
	private Integer id;
	private String name;
	private String alias;
	public Category( Integer id,String name, String alias,List<SubCategory> subcategory) {
		super();
		this.id=id;
		this.name = name;
		this.alias = alias;
		this.subcategory = subcategory;
	}

	public Category(String name, String alias) {
		super();
		this.name = name;
		this.alias = alias;
	}

	private List<SubCategory> subcategory = new ArrayList<SubCategory>();

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", alias=" + alias + ", subcategory=" + subcategory + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
