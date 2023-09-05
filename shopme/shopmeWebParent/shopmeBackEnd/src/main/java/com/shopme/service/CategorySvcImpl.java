package com.shopme.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopme.model.Category;
import com.shopme.model.SubCategory;
import com.shopme.util.ImageUtil;
import com.shopme.repo.CategoryRepo;
import com.shopme.repo.SubCategoryRepo;

@Service
public class CategorySvcImpl implements CategorySvc {
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	SubCategoryRepo subCategoryRepo;

	@Override
	public boolean add(Category category, SubCategory subcategory) {
		boolean result = true;
		List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();

		com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(subcategory.getName(),
				subcategory.getImage(), subcategory.getAlias(), subcategory.isEnabled(), category.getName());
		sublist.add(subobj);
		com.shopme.entity.Category cat = new com.shopme.entity.Category(category.getName(), category.getAlias(),
				sublist);
		try {
			categoryRepo.save(cat);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		List<com.shopme.entity.Category> entitylist = categoryRepo.findAll();
		List<Category> modellist = new ArrayList<Category>();
		for (com.shopme.entity.Category c : entitylist) {
			Category cat = new Category(c.getId(), c.getName(), c.getAlias(), c.getSubcategory());
			modellist.add(cat);
		}
		return modellist;
	}

	@Override
	public Category get(int id) {
		if (!categoryRepo.existsById(id)) {
			return null;
		}
		com.shopme.entity.Category single = categoryRepo.findById(id).get();
		System.out.println(single.getId() + "ee " + single.getName() + " eee" + single.getAlias());

		Category category = new Category(single.getId(), single.getName(), single.getAlias(), single.getSubcategory());
		return category;

	}

	@Override
	public boolean edit(Category category) {

		boolean result = false;
		if (categoryRepo.existsById(category.getId())) {
//			com.shopme.entity.Category c = categoryRepo.findById(category.getId()).get();

		}
		return result;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			subCategoryRepo.deleteById(id);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("in service delet");
		}
		return !subCategoryRepo.existsById(id);
	}

	@Override
	public boolean addSub(Integer id, com.shopme.model.SubCategory subcategory) {
		boolean result = true;
		Integer lastSubId = null;
		List<com.shopme.entity.SubCategory> entitylist = subCategoryRepo.findAll();
		for (com.shopme.entity.SubCategory c : entitylist) {
			lastSubId = c.getId();
		}
		com.shopme.entity.Category single = categoryRepo.findById(id).get();
		List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
		for (com.shopme.entity.SubCategory c : single.getSubcategory()) {
			sublist.add(c);
		}
		lastSubId += 1;
		com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(lastSubId, subcategory.getImage(),
				subcategory.getName(), subcategory.getAlias(), single.getName(), subcategory.isEnabled());
		sublist.add(subobj);
		com.shopme.entity.Category category = new com.shopme.entity.Category(id, single.getName(), single.getAlias(),
				sublist);
		try {
			categoryRepo.saveAndFlush(category);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public byte[] getImageById(int id) {
		com.shopme.entity.SubCategory sub = subCategoryRepo.findById(id).get();

		return ImageUtil.decompressImage(sub.getImage());
	}

	@Override
	public void enable(Integer id, Integer parentid) {
		com.shopme.entity.SubCategory sub1;
		com.shopme.entity.Category single = categoryRepo.findById(parentid).get();
		List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
		for (com.shopme.entity.SubCategory c : single.getSubcategory()) {
			if (c.getId() != id) {
				sub1 = new com.shopme.entity.SubCategory(c.getId(), c.getImage(), c.getName(), c.getAlias(),
						c.getParentName(), c.isEnabled());
			} else {
				sub1 = new com.shopme.entity.SubCategory(c.getId(), c.getImage(), c.getName(), c.getAlias(),
						c.getParentName(), !c.isEnabled());
			}
			sublist.add(sub1);

		}
		com.shopme.entity.Category category = new com.shopme.entity.Category(parentid, single.getName(),
				single.getAlias(), sublist);
		categoryRepo.saveAndFlush(category);
	}

	@Override
	public List<SubCategory> getAllSub() {
		List<com.shopme.entity.SubCategory> entitylist = subCategoryRepo.findAll();
		List<SubCategory> modellist = new ArrayList<SubCategory>();
		for (com.shopme.entity.SubCategory c : entitylist) {
			SubCategory cat = new SubCategory(c.getId(), c.getName(), c.getAlias(), c.isEnabled(), c.getParentName());
			modellist.add(cat);
		}

		return modellist;

	}

}
