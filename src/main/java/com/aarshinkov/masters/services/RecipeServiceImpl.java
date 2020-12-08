package com.aarshinkov.masters.services;

import com.aarshinkov.masters.collections.ObjCollection;
import com.aarshinkov.masters.collections.RecipesCollection;
import com.aarshinkov.masters.dao.RecipesDao;
import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.models.recipes.RecipeCreateModel;
import com.aarshinkov.masters.models.recipes.RecipeUpdateModel;
import com.aarshinkov.masters.repositories.RecipesRepository;
import com.aarshinkov.masters.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private RecipesDao recipesDao;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ObjCollection<RecipeEntity> getRecipes(String query) {
        List<RecipeEntity> recipes = recipesRepository.findByTitleContainingIgnoreCase(query);

        ObjCollection<RecipeEntity> collection = new RecipesCollection<>();
        collection.setCollection(recipes);
        collection.setItemsCount((long) recipes.size());

        return collection;
    }

    @Override
    public ObjCollection<RecipeEntity> getRecipes() {
        List<RecipeEntity> recipes = recipesRepository.findAll();

        ObjCollection<RecipeEntity> collection = new RecipesCollection<>();
        collection.setCollection(recipes);
        collection.setItemsCount((long) recipes.size());

        return collection;
    }

    @Override
    public RecipeEntity getRecipeByRecipeId(Long recipeId) {
        return recipesRepository.findByRecipeId(recipeId);
    }

    @Override
    public List<RecipeEntity> getLastNRecipes(Integer count) {
        return recipesDao.getLastNRecipes(count);
    }

    @Override
    public RecipeEntity createRecipe(RecipeCreateModel rcm) throws Exception {

        UserEntity user = usersRepository.findByUserId(rcm.getUserId());

        if (user == null) {
            throw new Exception("User with ID " + rcm.getUserId() + " does not exist");
        }

        RecipeEntity recipe = new RecipeEntity();
        recipe.setPrepareTime(rcm.getPrepareTime());
        recipe.setCookingTime(rcm.getCookingTime());
        recipe.setPortions(rcm.getPortions());
        recipe.setTitle(rcm.getTitle());
        recipe.setRecipe(rcm.getRecipe());
        recipe.setUser(user);

        return recipesRepository.save(recipe);
    }

    @Override
    public RecipeEntity updateRecipe(RecipeUpdateModel rum) throws Exception {

        RecipeEntity recipe = recipesRepository.findByRecipeId(rum.getRecipeId());

        if (recipe == null) {
            throw new Exception("Recipe with ID " + rum.getRecipeId() + " does not exist");
        }

        recipe.setPrepareTime(rum.getPrepareTime());
        recipe.setCookingTime(rum.getCookingTime());
        recipe.setPortions(rum.getPortions());
        recipe.setTitle(rum.getTitle());
        recipe.setRecipe(rum.getRecipe());

        UserEntity author = usersRepository.findByUserId(rum.getUserId());

        if (author == null) {
            throw new Exception("User with ID " + rum.getUserId() + " does not exist");
        }

        recipe.setUser(author);
        recipe.setEditedOn(new Timestamp(System.currentTimeMillis()));

        return recipesRepository.save(recipe);
    }

    @Override
    public RecipeEntity deleteRecipe(Long recipeId) throws Exception {

        RecipeEntity recipe = recipesRepository.findByRecipeId(recipeId);

        if (recipe == null) {
            throw new Exception("Recipe with ID " + recipeId + " does not exist");
        }

        recipesRepository.delete(recipe);

        return recipe;
    }
}