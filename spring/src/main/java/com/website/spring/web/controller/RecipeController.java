package com.website.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RecipeController {


    @ResponseBody
    @GetMapping("welcome")
    public String demo(){
       // log.info("demo methot called");
        return "hello spring";
    }

    
}
