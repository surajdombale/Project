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
	public boolean edit(SubCategory subcategory) {
		boolean repeat = true;
		int lastCatId = 0;
		int id = 0;
		boolean result = false;
		if (subCategoryRepo.existsById(subcategory.getId())) {

			List<com.shopme.entity.Category> categoryList = categoryRepo.findAll();
			for (com.shopme.entity.Category i : categoryList) {
				lastCatId = i.getId();
				System.out.println(i.getName() + " " + subcategory.getParentName() + "" + lastCatId);
				if (i.getName().contentEquals(subcategory.getParentName())) {
					System.out.println("in if");
					id = i.getId();
					repeat = false;
				}
			}

			if (repeat) {
				result = true;
				System.out.println("in new");
				List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
				com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(subcategory.getId(),
						subcategory.getImage(), subcategory.getName(), subcategory.getAlias(),
						subcategory.getParentName(), subcategory.isEnabled());
				sublist.add(subobj);
				com.shopme.entity.Category cat = new com.shopme.entity.Category(lastCatId + 1,
						subcategory.getParentName(), subcategory.getParentName(), sublist);
				try {
					subCategoryRepo.deleteById(subcategory.getId());
					categoryRepo.save(cat);
				} catch (Exception e) {
					result = false;
					e.printStackTrace();
				}

			} else {
				result = true;
				subCategoryRepo.deleteById(subcategory.getId());
				System.out.println("in exist");
				com.shopme.entity.Category single = categoryRepo.findById(id).get();
				List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
				for (com.shopme.entity.SubCategory c : single.getSubcategory()) {
					sublist.add(c);
				}

				com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(subcategory.getId(),
						subcategory.getImage(), subcategory.getName(), subcategory.getAlias(), single.getName(),
						subcategory.isEnabled());
				sublist.add(subobj);
				com.shopme.entity.Category category = new com.shopme.entity.Category(id, single.getName(),
						single.getAlias(), sublist);
				try {
					categoryRepo.saveAndFlush(category);
				} catch (Exception e) {
					result = false;
					e.printStackTrace();
				}
			}

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
	public Integer addSub(String parentName, com.shopme.model.SubCategory subcategory) {

		boolean repeat = true;
		int lastSubId = 1;
		int lastCatId = 1;
		int id = 0;
		System.out.println("fffff");
		List<com.shopme.entity.SubCategory> entitylist = subCategoryRepo.findAll();

		for (com.shopme.entity.SubCategory c : entitylist) {
			lastSubId = c.getId() + 1;
			System.out.println(c.getName() + " " + subcategory.getName() + " " + lastSubId);
			if (subcategory.getName().contentEquals(c.getName())) {
				System.out.println("just here");
				return 0;
			}
		}
		List<com.shopme.entity.Category> categoryList = categoryRepo.findAll();
		for (com.shopme.entity.Category i : categoryList) {
			lastCatId = i.getId()+1;
			System.out.println(i.getName() + " " + parentName + "" + lastCatId);
			if (i.getName().contentEquals(parentName)) {
				System.out.println("in if");
				id = i.getId();
				repeat = false;
			}
		}

		if (repeat) {
			System.out.println("in new");
			List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
			com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(lastSubId, subcategory.getImage(),
					subcategory.getName(), subcategory.getAlias(), parentName, subcategory.isEnabled());
			sublist.add(subobj);
			com.shopme.entity.Category cat = new com.shopme.entity.Category(lastCatId, parentName, parentName,
					sublist);
			try {
				categoryRepo.save(cat);
			} catch (Exception e) {
				lastSubId = 0;
				e.printStackTrace();
			}

		} else {

			System.out.println("in exist");
			com.shopme.entity.Category single = categoryRepo.findById(id).get();
			List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
			for (com.shopme.entity.SubCategory c : single.getSubcategory()) {
				sublist.add(c);
			}

			com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(lastSubId, subcategory.getImage(),
					subcategory.getName(), subcategory.getAlias(), single.getName(), subcategory.isEnabled());
			sublist.add(subobj);
			com.shopme.entity.Category category = new com.shopme.entity.Category(id, single.getName(),
					single.getAlias(), sublist);
			try {
				categoryRepo.saveAndFlush(category);
			} catch (Exception e) {
				lastSubId = 0;
				e.printStackTrace();

			}

		}
		return lastSubId;
	}

	@Override
	public byte[] getImageById(int id) {
		com.shopme.entity.SubCategory sub = subCategoryRepo.findById(id).get();

		return ImageUtil.decompressImage(sub.getImage());
	}

	@Override
	public boolean enable(Integer id) {
		Integer parentid = null;
		boolean result = false;
		com.shopme.entity.SubCategory sub1 = subCategoryRepo.findById(id).get();
		List<com.shopme.entity.Category> single1 = new ArrayList<com.shopme.entity.Category>();
		single1 = categoryRepo.findAll();
		for (com.shopme.entity.Category a : single1) {
			if (a.getName().contains(sub1.parentName)) {
				parentid = a.getId();
			}
		}
		com.shopme.entity.Category single = categoryRepo.findById(parentid).get();
		List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
		for (com.shopme.entity.SubCategory c : single.getSubcategory()) {
			if (c.getId() != id) {
				sub1 = new com.shopme.entity.SubCategory(c.getId(), c.getImage(), c.getName(), c.getAlias(),
						c.getParentName(), c.isEnabled());
			} else {
				result = !c.isEnabled();
				sub1 = new com.shopme.entity.SubCategory(c.getId(), c.getImage(), c.getName(), c.getAlias(),
						c.getParentName(), !c.isEnabled());
			}
			sublist.add(sub1);

		}
		com.shopme.entity.Category category = new com.shopme.entity.Category(parentid, single.getName(),
				single.getAlias(), sublist);
		categoryRepo.saveAndFlush(category);
		return result;
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

	@Override
	public SubCategory getById(Integer id) {
		com.shopme.entity.SubCategory c = subCategoryRepo.findById(id).get();
		SubCategory cat = new SubCategory(c.getId(), c.getName(), c.getAlias(), c.isEnabled(), c.getParentName());
		return cat;
	}

	@Override
	public boolean editWithoutPhoto(SubCategory subcategory) {
		boolean repeat = true;
		int lastCatId = 0;
		int id = 0;
		boolean result = false;
		if (subCategoryRepo.existsById(subcategory.getId())) {
			com.shopme.entity.SubCategory sub = subCategoryRepo.findById(subcategory.getId()).get();
			
			List<com.shopme.entity.Category> categoryList = categoryRepo.findAll();
			for (com.shopme.entity.Category i : categoryList) {
				lastCatId = i.getId();
				System.out.println(i.getName() + " " + subcategory.getParentName() + "" + lastCatId);
				if (i.getName().contentEquals(subcategory.getParentName())) {
					System.out.println("in if");
					id = i.getId();
					repeat = false;
				}
			}

			if (repeat) {
				result = true;
				System.out.println("in new");
				List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
				com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(subcategory.getId(),
						sub.getImage(), subcategory.getName(), subcategory.getAlias(), subcategory.getParentName(),
						subcategory.isEnabled());
				sublist.add(subobj);
				com.shopme.entity.Category cat = new com.shopme.entity.Category(lastCatId + 1,
						subcategory.getParentName(), subcategory.getParentName(), sublist);
				try {
					subCategoryRepo.deleteById(subcategory.getId());
					categoryRepo.saveAndFlush(cat);
				} catch (Exception e) {
					result = false;
					e.printStackTrace();
				}

			} else {
				result = true;
				subCategoryRepo.deleteById(subcategory.getId());
				System.out.println("in exist");
				com.shopme.entity.Category single = categoryRepo.findById(id).get();
				List<com.shopme.entity.SubCategory> sublist = new ArrayList<com.shopme.entity.SubCategory>();
				for (com.shopme.entity.SubCategory c : single.getSubcategory()) {
					sublist.add(c);
				}

				com.shopme.entity.SubCategory subobj = new com.shopme.entity.SubCategory(subcategory.getId(),
						sub.getImage(), subcategory.getName(), subcategory.getAlias(), single.getName(),
						subcategory.isEnabled());
				sublist.add(subobj);
				com.shopme.entity.Category category = new com.shopme.entity.Category(id, single.getName(),
						single.getAlias(), sublist);
				try {
					categoryRepo.saveAndFlush(category);
				} catch (Exception e) {
					result = false;
					e.printStackTrace();
				}
			}

		}
		return result;
	}

}
