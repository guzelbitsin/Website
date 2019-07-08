package com.website.spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Setter
@Getter
@ToString

@Document(collection = "Recipe")
public class Recipe {


    private long id;

    @Id
    private String name;
    private String description;
    private String tag;
    private String image;

    public Recipe(String name,String description,String tag,String image){
        this.id = (int) (new Date().getTime()/1000);
        this.name=name;
        this.tag=tag;
        this.image=image;
        this.description=description;
    }
}
