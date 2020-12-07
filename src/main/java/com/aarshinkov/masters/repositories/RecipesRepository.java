package com.aarshinkov.masters.repositories;

import com.aarshinkov.masters.entities.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<RecipeEntity, Long> {
    RecipeEntity findByRecipeId(Long recipeId);

    List<RecipeEntity> findByTitleContainingIgnoreCase(String title);
}
