package com.aarshinkov.masters.repositories;

import com.aarshinkov.masters.entities.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<RecipeEntity, Long> {
    List<RecipeEntity> findAllByOrderByCreatedOnDesc();

    RecipeEntity findByRecipeId(Long recipeId);

    List<RecipeEntity> findByTitleContainingIgnoreCase(String title);

    List<RecipeEntity> findByCategoryCategoryId(Long categoryId);

    List<RecipeEntity> findByTitleContainingIgnoreCaseAndCategoryCategoryId(String title, Long categoryId);

}
