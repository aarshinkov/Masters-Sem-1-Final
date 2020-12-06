package com.aarshinkov.masters.controllers;

import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.models.recipes.RecipeCreateModel;
import com.aarshinkov.masters.models.recipes.RecipeUpdateModel;
import com.aarshinkov.masters.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RecipesController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public List<RecipeEntity> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/recipes/{recipeId}")
    public RecipeEntity getRecipe(@PathVariable("recipeId") Long recipeId) {
        return recipeService.getRecipeByRecipeId(recipeId);
    }

    @PostMapping("/recipes")
    public RecipeEntity createRecipe(@RequestBody RecipeCreateModel rcm) throws Exception {
        return recipeService.createRecipe(rcm);
    }

    @PutMapping("/recipes")
    public RecipeEntity updateRecipe(@RequestBody RecipeUpdateModel rum) throws Exception {
        return recipeService.updateRecipe(rum);
    }

    @DeleteMapping("/recipes/{recipeId}")
    public RecipeEntity deleteRecipe(@PathVariable("recipeId") Long recipeId) throws Exception {
        return recipeService.deleteRecipe(recipeId);
    }
}
