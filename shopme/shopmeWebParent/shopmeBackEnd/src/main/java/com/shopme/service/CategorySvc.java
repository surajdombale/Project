package com.shopme.service;

import java.util.List;

import com.shopme.model.Category;
import com.shopme.model.SubCategory;

public interface CategorySvc {
	public boolean addSub(Integer id, SubCategory subCategory);

	public boolean add(Category category, SubCategory subCategory);

	public List<Category> getAll();

	public Category get(int id);

	public byte[] getImageById(int id);

	public boolean edit(Category category);

	public boolean delete(Integer id);

	public void enable(Integer id, Integer parentId);

	public List<SubCategory> getAllSub();
}
