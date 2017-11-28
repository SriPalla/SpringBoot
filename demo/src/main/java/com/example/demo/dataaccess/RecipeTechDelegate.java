package com.example.demo.dataaccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeTechDelegate {
	@Autowired RecipeDao recipeDao;
	
	@Transactional(readOnly = true)
	public List<RecipeDto> getAllRecipe() {
		return recipeDao.getAllRecipe();
	}

	@Transactional(readOnly = true)
	public RecipeDto getSpecificRecipe(int id) {
		return recipeDao.getSpecificRecipe(id);
	}
}
