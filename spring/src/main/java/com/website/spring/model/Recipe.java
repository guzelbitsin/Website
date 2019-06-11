package com.website.spring.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Recipe {
    private int id;
    private String name;
    private String description;
    private String tag;

}
