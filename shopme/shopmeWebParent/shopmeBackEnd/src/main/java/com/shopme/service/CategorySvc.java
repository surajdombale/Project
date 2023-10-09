package com.shopme.service;

import java.util.List;

import com.shopme.model.Category;
import com.shopme.model.SubCategory;

public interface CategorySvc {
	public Integer addSub(String parentName, SubCategory subCategory);

	public boolean add(Category category, SubCategory subCategory);

	public List<Category> getAll();

	public Category get(int id);

	public byte[] getImageById(int id);

	public boolean edit(SubCategory subCategory);

	public boolean delete(Integer id);

	public boolean enable(Integer id);

	public List<SubCategory> getAllSub();

	public SubCategory getById(Integer id);

	public boolean editWithoutPhoto(SubCategory subCategory);
}