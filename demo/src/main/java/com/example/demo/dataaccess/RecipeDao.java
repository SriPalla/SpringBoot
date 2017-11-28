package com.example.demo.dataaccess;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class RecipeDao extends AbstractDao {
	@SuppressWarnings("unchecked")
	public List<RecipeDto> getAllRecipe() {
		List<RecipeDto> returnValue = new ArrayList<>();
		List<RecipeEntity> data = getSession().createCriteria(RecipeEntity.class).list();
		for(RecipeEntity entity : data) {
			RecipeDto dto = new RecipeDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			returnValue.add(dto);
		}
		return returnValue;
	}

	public RecipeDto getSpecificRecipe(int id) {
		RecipeEntity entity = getSession().get(RecipeEntity.class, 1);
		RecipeDto returnValue =new RecipeDto();
		returnValue.setId(entity.getId());
		returnValue.setName(entity.getName());
		return returnValue;
	}
}
