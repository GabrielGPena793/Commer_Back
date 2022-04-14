package com.dh.commerce.services;

import com.dh.commerce.dto.ProductCartDTO;
import com.dh.commerce.dto.ProductDTO;
import com.dh.commerce.dto.ProductLongCategoryDTO;
import com.dh.commerce.entities.Categories;
import com.dh.commerce.entities.Product;
import com.dh.commerce.repositories.CategoriesRepository;
import com.dh.commerce.repositories.ProductRepository;
import com.dh.commerce.services.Exceptions.EntitieNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Transactional
    public ProductDTO post(ProductLongCategoryDTO productLongCategoryDTO){

        Optional<Categories> categories = categoriesRepository.findById(productLongCategoryDTO.getCategory());
        if (categories.isEmpty()){
            throw new EntitieNotFoundException("Entity not found!");
        }

        Product product = new Product();
        BeanUtils.copyProperties(productLongCategoryDTO, product);
        product.setCategory(categories.get());
        product = productRepository.save(product);
        return new ProductDTO(product);

    }

    public Page<ProductDTO> findAll(Pageable pageable){
        return productRepository.findAll(pageable).map(ProductDTO::new);
    }

    public ProductDTO findById(Long id){

        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new EntitieNotFoundException("Entity not found!");
        }

        return new ProductDTO(product.get());
    }

    @Transactional
    public ProductDTO put(ProductLongCategoryDTO productDTO){

        Product product = productRepository.findById(productDTO.getId()).orElse(null);
        if (product == null){
            throw new EntitieNotFoundException("Entity not found!");
        }
        BeanUtils.copyProperties(productDTO, product);
        product.setCategory(categoriesRepository.findById(productDTO.getCategory()).orElse(null));
        return new ProductDTO(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id){

        if (productRepository.findById(id).isEmpty()){
            throw new EntitieNotFoundException("Entity not found");
        }

        productRepository.deleteById(id);
    }

    @Transactional
    public List<ProductDTO> cartValuesAtt(List<ProductCartDTO> cartProducts){

        return cartProducts.stream().map(cartProduct -> {
            ProductDTO productDTO = new ProductDTO();
            Product product = productRepository.findById(cartProduct.getId()).orElse(null);
            BeanUtils.copyProperties(product, productDTO);
            productDTO.setPrice(product.getPrice() * cartProduct.getQuantity());
            return productDTO;
        }).collect(Collectors.toList());
    }
}
