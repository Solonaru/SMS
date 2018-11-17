package com.project.sms.entities.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;

	public Optional<Category> findCategoryById(int categoryId) {
		return categoryRepository.findById(categoryId);
	}

	public List<Category> findAllCategories() {
		return (List<Category>) categoryRepository.findAll();
	}

	public void insertCategory(Category category) {
		categoryRepository.save(category);
	}

	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}

	public void deleteCategoryById(int categoryId) {
		categoryRepository.deleteById(categoryId);
	}
}
