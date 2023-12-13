package com.example.demo.controller;

import com.example.demo.service.ChronoFunctionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ChronoFunctionalService service;

    @GetMapping("/search")
    public String search() {
        return "ok";
    }

    @GetMapping("/findAll")
    public List<String> findAll(){
        return service.findAllName();
    }
}
