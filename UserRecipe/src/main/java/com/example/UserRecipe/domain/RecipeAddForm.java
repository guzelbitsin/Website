package com.example.UserRecipe.domain;


import javax.validation.constraints.Size;

public class RecipeAddForm {

    @Size(min = 2, max = 250)
    private String recipeDesc;

    @Size(min = 2, max = 250)
    private String recipeName;
    @Size(min = 2, max = 250)
    private String recipeTag;
    @Size(min = 2, max = 250)
    private String recipeImage;

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeTag() {
        return recipeTag;
    }

    public void setRecipeTag(String recipeTag) {
        this.recipeTag = recipeTag;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }
}

