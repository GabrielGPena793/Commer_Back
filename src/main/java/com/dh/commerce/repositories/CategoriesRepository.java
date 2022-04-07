package com.dh.commerce.repositories;

import com.dh.commerce.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Query("SELECT name FROM Categories")
    List<String> categoriesNames();

}