package com.aarshinkov.masters.services;

import com.aarshinkov.masters.collections.ObjCollection;
import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.models.recipes.RecipeCreateModel;
import com.aarshinkov.masters.models.recipes.RecipeUpdateModel;

import java.util.List;

public interface RecipeService {

    ObjCollection<RecipeEntity> getRecipes(String query);

    ObjCollection<RecipeEntity> getRecipes();

    RecipeEntity getRecipeByRecipeId(Long recipeId);

    List<RecipeEntity> getLastNRecipes(Integer count);

    RecipeEntity createRecipe(RecipeCreateModel rcm) throws Exception;

    RecipeEntity updateRecipe(RecipeUpdateModel rum) throws Exception;

    RecipeEntity deleteRecipe(Long recipeId) throws Exception;
}
