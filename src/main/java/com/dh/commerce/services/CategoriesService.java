package com.dh.commerce.services;

import com.dh.commerce.entities.Categories;
import com.dh.commerce.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public void post(Categories categories){
        categoriesRepository.save(categories);
    }

    public List<Categories> findAll(){
        return categoriesRepository.findAll();
    }

    public Categories findById(Long id){
       return  categoriesRepository.findById(id).orElse(null);
    }

    public List<String> categoriesName(){
        return categoriesRepository.categoriesNames();
    }
}
