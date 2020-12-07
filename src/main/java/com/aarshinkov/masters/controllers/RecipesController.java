package com.aarshinkov.masters.controllers;

import com.aarshinkov.masters.collections.ObjCollection;
import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.models.recipes.RecipeCreateModel;
import com.aarshinkov.masters.models.recipes.RecipeUpdateModel;
import com.aarshinkov.masters.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecipesController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public String getRecipes(@RequestParam(value = "q", required = false) String query, Model model) {
        ObjCollection<RecipeEntity> recipes;

        if (query != null) {
            recipes = recipeService.getRecipes(query);
        } else {
            recipes = recipeService.getRecipes();
        }

        model.addAttribute("recipes", recipes);

        return "food/recipes";
    }

    @GetMapping("/recipe/{recipeId}")
    public String getRecipe(@PathVariable("recipeId") Long recipeId, Model model) {
        RecipeEntity recipe = recipeService.getRecipeByRecipeId(recipeId);

        model.addAttribute("recipe", recipe);

        return "food/recipe";
    }

    @GetMapping(value = "/recipes/lastN", params = "count")
    @ResponseBody
    public List<RecipeEntity> lastNPublishedRecipes(@RequestParam("count") Integer count) {
        return recipeService.getLastNRecipes(count);
    }

    @GetMapping("/recipe/create")
    public String openCreateRecipe() {
        return "food/create";
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
