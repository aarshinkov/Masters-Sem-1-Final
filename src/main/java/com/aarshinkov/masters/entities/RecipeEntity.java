package com.aarshinkov.masters.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "recipes")
public class RecipeEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "prepare_time")
    private Integer prepareTime;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "portions")
    private Integer portions;

    @Column(name = "title")
    private String title;

    @Column(name = "ingredients", length = 600)
    private String ingredients;

    @Column(name = "recipe", length = 3000)
    private String recipe;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
//    @JsonIgnore
    private CategoryEntity category;

    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "edited_on")
    private Timestamp editedOn;
}
