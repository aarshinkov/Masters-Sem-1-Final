package com.aarshinkov.masters.rest;

import com.aarshinkov.masters.collections.ObjCollection;
import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.models.recipes.RecipeCreateModel;
import com.aarshinkov.masters.models.recipes.RecipeUpdateModel;
import com.aarshinkov.masters.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecipesController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public ObjCollection<RecipeEntity> getRecipes(@RequestParam(value = "q", required = false) String query) {
        if (query != null) {
            return recipeService.getRecipes(query);
        }

        return recipeService.getRecipes();
    }

    @GetMapping("/recipes/{recipeId}")
    public RecipeEntity getRecipe(@PathVariable("recipeId") Long recipeId) {
        return recipeService.getRecipeByRecipeId(recipeId);
    }

    @GetMapping(value = "/recipes/lastN", params = "count")
    public List<RecipeEntity> lastNPublishedRecipes(@RequestParam("count") Integer count) {
        return recipeService.getLastNRecipes(count);
    }

    @PostMapping("/recipes")
    public RecipeEntity createRecipe(RecipeCreateModel rcm) throws Exception {
        return recipeService.createRecipe(rcm);
    }

    @PutMapping("/recipes")
    public RecipeEntity updateRecipe(RecipeUpdateModel rum) throws Exception {
        return recipeService.updateRecipe(rum);
    }

    @DeleteMapping("/recipes/{recipeId}")
    public RecipeEntity deleteRecipe(@PathVariable("recipeId") Long recipeId) throws Exception {
        return recipeService.deleteRecipe(recipeId);
    }
}
