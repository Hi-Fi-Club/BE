package com.teamspace.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class SwaggerController {

    @GetMapping("/api")
    public String getApiDocs(){
        return "redirect:/swagger-ui/index.html";
    }

}
