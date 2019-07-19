package com.example.UserRecipe.repository;

import java.util.List;

import com.example.UserRecipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
       Iterable<Recipe> findByName(String name);
       List<Recipe> findByTag(String tag);


}
