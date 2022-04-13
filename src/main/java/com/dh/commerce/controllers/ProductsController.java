package com.dh.commerce.controllers;

import com.dh.commerce.dto.ProductDTO;
import com.dh.commerce.dto.ProductLongCategoryDTO;
import com.dh.commerce.entities.Product;
import com.dh.commerce.services.CategoriesService;
import com.dh.commerce.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private ResponseEntity<ProductDTO> post(@RequestBody ProductLongCategoryDTO productLongCategoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.post(productLongCategoryDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.findById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> findAllCategories(){
        return ResponseEntity.ok().body(categoriesService.categoriesName());
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductLongCategoryDTO productLongCategoryDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productsService.put(productLongCategoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
    }

}
