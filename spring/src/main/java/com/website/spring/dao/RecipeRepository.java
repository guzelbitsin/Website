package com.website.spring.dao;

import com.website.spring.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Recipe findByTag(String tag);
    Recipe findByName(String name);

}
