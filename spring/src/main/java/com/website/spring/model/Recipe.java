package com.website.spring.model;

import lombok.Data;

@Data
public class Recipe {
    private int id;
    private String name;
    private String description;
    private String tag;

}
