package com.example.UserRecipe.repository;

import com.example.UserRecipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
       Iterable<Recipe> findByName(String name);
       List<Recipe> findByTag(String tag);


}
