package com.dh.commerce.services;

import com.dh.commerce.dto.ProductDTO;
import com.dh.commerce.entities.Product;
import com.dh.commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesService categoriesService;

    public Product post(ProductDTO productDTO){

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setCategory(categoriesService.findById(productDTO.getCategory()));

        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product put(ProductDTO productDTO){

        Product product = findById(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setCategory(categoriesService.findById(productDTO.getCategory()));

        return productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

}
