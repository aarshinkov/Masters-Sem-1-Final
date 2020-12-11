package com.aarshinkov.masters.security;

import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class Expressions {

    @Autowired
    private RecipeService recipeService;

    public boolean isUserAuthor(Long userId, Long recipeId) {
        if (userId == null || recipeId == null) return false;

        RecipeEntity recipe = recipeService.getRecipeByRecipeId(recipeId);

        if (recipe == null) return false;

        return userId.equals(recipe.getUser().getUserId());
    }

    public boolean isUserAuthor(HttpServletRequest request, Long recipeId) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        if (user.getUserId() == null || recipeId == null) return false;

        RecipeEntity recipe = recipeService.getRecipeByRecipeId(recipeId);

        if (recipe == null) return false;

        return user.getUserId().equals(recipe.getUser().getUserId());
    }
}
