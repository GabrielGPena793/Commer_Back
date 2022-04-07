package com.dh.commerce.controllers;

import com.dh.commerce.dto.ProductDTO;
import com.dh.commerce.entities.Product;
import com.dh.commerce.services.CategoriesService;
import com.dh.commerce.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController  {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CategoriesService categoriesService;


    @PostMapping
    private ResponseEntity<Product> post(@RequestBody ProductDTO productDTO){

        return ResponseEntity.ok(productsService.post(productDTO));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){

        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){

        if (productsService.findById(id) != null){
            return ResponseEntity.ok(productsService.findById(id));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> findAllCategories(){
        return ResponseEntity.ok().body(categoriesService.categoriesName());
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody ProductDTO productDTO){

        if (productsService.findById(productDTO.getId()) != null){
            return ResponseEntity.ok(productsService.put(productDTO));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

        if (productsService.findById(id) != null){
            productsService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
