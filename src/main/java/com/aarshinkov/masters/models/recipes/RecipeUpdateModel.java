package com.aarshinkov.masters.models.recipes;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeUpdateModel implements Serializable {

    private String title;
    private Long categoryId;
    private Long recipeId;
    private Integer prepareTime;
    private Integer cookingTime;
    private Integer portions;
    private String ingredients;
    private String recipe;
    private String imagePath;
    private Long userId;
}
