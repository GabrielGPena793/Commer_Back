package com.dh.commerce.repositories;

import com.dh.commerce.dto.ProductDTO;
import com.dh.commerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT obj FROM Product obj WHERE category_id = ?1")
    public List<Product> acharCategory(Long categoryId);
}