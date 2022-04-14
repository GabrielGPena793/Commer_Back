package com.dh.commerce.controllers;

import com.dh.commerce.dto.CategoriesDTO;
import com.dh.commerce.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<CategoriesDTO> post(@RequestBody CategoriesDTO categoriesDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriesService.post(categoriesDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoriesDTO>> findAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoriesService.findById(id));
    }

}
