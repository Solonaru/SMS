package com.project.sms.entities.category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
	
	public Optional<Category> findCategoryById(int categoryId);

	public List<Category> findAllCategories();

	public void insertCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategoryById(int categoryId);
}