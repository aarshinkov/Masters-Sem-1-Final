package com.aarshinkov.masters.dao;

import com.aarshinkov.masters.entities.RecipeEntity;

import java.util.List;

public interface RecipesDao {
    List<RecipeEntity> getLastNRecipes(Integer count);
}
