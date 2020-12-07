package com.aarshinkov.masters.models.recipes;

import com.aarshinkov.masters.entities.UserEntity;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeCreateModel implements Serializable {

    private Integer prepareTime;
    private Integer cookingTime;
    private Integer portions;
    private String title;
    private String recipe;
    private String imagePath;
    private Long userId;
}
