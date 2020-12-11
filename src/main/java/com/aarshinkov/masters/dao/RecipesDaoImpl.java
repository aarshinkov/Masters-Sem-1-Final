package com.aarshinkov.masters.dao;

import com.aarshinkov.masters.entities.CategoryEntity;
import com.aarshinkov.masters.entities.RecipeEntity;
import com.aarshinkov.masters.repositories.CategoriesRepository;
import com.aarshinkov.masters.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RecipesDaoImpl implements RecipesDao {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RecipeEntity> getLastNRecipes(Integer count) {

        String sql = "SELECT * FROM recipes ORDER BY created_on DESC LIMIT ?";

        try (Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, count);
            ResultSet rset = pstmt.executeQuery();

            List<RecipeEntity> recipes = new ArrayList<>();

            while (rset.next()) {
                RecipeEntity recipe = new RecipeEntity();
                recipe.setRecipeId(rset.getLong("recipe_id"));
                recipe.setTitle(rset.getString("title"));

                CategoryEntity category = categoriesRepository.findByCategoryId(rset.getLong("category_id"));

                recipe.setCategory(category);
                recipe.setPrepareTime(rset.getInt("prepare_time"));
                recipe.setCookingTime(rset.getInt("cooking_time"));
                recipe.setPortions(rset.getInt("portions"));
                recipe.setIngredients(rset.getString("ingredients"));
                recipe.setRecipe(rset.getString("recipe"));
                recipe.setImagePath(rset.getString("image_path"));

                recipe.setUser(usersRepository.findByUserId(rset.getLong("user_id")));

                recipe.setCreatedOn(rset.getTimestamp("created_on"));
                recipe.setEditedOn(rset.getTimestamp("edited_on"));

                recipes.add(recipe);
            }

            return recipes;
        } catch (Exception e) {
            log.debug("Error getting last " + count + " recipes", e);
        }

        return null;
    }
}
