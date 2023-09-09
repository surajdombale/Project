package com.shopme.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.shopme.model.Category;
import com.shopme.model.SubCategory;
import com.shopme.service.CategorySvc;
import com.shopme.util.ImageUtil;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
	@Autowired
	CategorySvc categorysvc;

	@GetMapping("/status")
	public String isServiceRunning() {
		return " Category Service Is Running";
	}

	@PostMapping("/newsub")
	public boolean createSubCategory(@RequestParam("id") final Integer parentid,
			@RequestParam("name") final String name, @RequestParam("alias") final String alias,
			@RequestParam("enabled") final boolean enabled, final @RequestParam("file") MultipartFile file)
			throws IOException {
		System.out.println(name + " " + parentid + " " + enabled);
		SubCategory sub = new SubCategory(ImageUtil.compressImage(file.getBytes()), name, alias, enabled);
		return categorysvc.addSub(parentid, sub);

	}

	@PostMapping
	public boolean addCategory(@RequestParam("categoryname") final String categoryname,
			@RequestParam("subcategoryname") final String subcategoryname,
			@RequestParam("categoryalias") final String categoryalias,
			@RequestParam("subcategoryalias") final String subcategoryalias,
			@RequestParam("enabled") final boolean enabled, final @RequestParam("file") MultipartFile file)
			throws IOException {
		System.out.println(
				subcategoryname + " " + subcategoryalias + " " + enabled + " " + categoryname + " " + categoryalias);
		SubCategory sub = new SubCategory(ImageUtil.compressImage(file.getBytes()), subcategoryname, subcategoryalias,
				enabled);
		Category cat = new Category(categoryname, categoryalias);
		return categorysvc.add(cat, sub);
	}

	@GetMapping
	public List<Category> getAllCategory() {
		System.out.println("in controller getall");
		return categorysvc.getAll();

	}

//	@GetMapping("{id}")
//	public Category getCategory(@PathVariable int id) {
//		System.out.println("in controller get");
//		return categorysvc.get(id);
//	}

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
		byte[] image = categorysvc.getImageById(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
	}

	@GetMapping("/getsub")
	public List<SubCategory> getAll() {
		return categorysvc.getAllSub();

	}

	@PutMapping
	public boolean editCategory(Category category) {
		System.out.println("in controller edit");
		return categorysvc.edit(category);
	}

	@DeleteMapping("{id}")
	public boolean deleteCategory(@PathVariable int id) {
		System.out.println("in controller delet");
		return categorysvc.delete(id);
	}

	@PostMapping("/enabled/{id}")
	public void enableCstegory(@PathVariable int id) {
		System.out.println(id);
		categorysvc.enable(id);
	}

}
