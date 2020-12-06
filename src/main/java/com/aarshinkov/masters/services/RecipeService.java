package com.aarshinkov.masters.services;

import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.models.recipes.RecipeCreateModel;
import com.aarshinkov.masters.models.recipes.RecipeUpdateModel;

import java.util.List;

public interface RecipeService {

    List<RecipeEntity> getRecipes();

    RecipeEntity getRecipeByRecipeId(Long recipeId);

    RecipeEntity createRecipe(RecipeCreateModel rcm) throws Exception;

    RecipeEntity updateRecipe(RecipeUpdateModel rum) throws Exception;

    RecipeEntity deleteRecipe(Long recipeId) throws Exception;
}
