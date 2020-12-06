package com.aarshinkov.masters.models.recipes;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeUpdateModel implements Serializable {

    private Long recipeId;
    private Integer prepareTime;
    private Integer cookingTime;
    private Integer portions;
    private String title;
    private String recipe;
    private Long userId;
}
