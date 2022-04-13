package com.dh.commerce.services;

import com.dh.commerce.dto.CategoriesDTO;
import com.dh.commerce.entities.Categories;
import com.dh.commerce.repositories.CategoriesRepository;
import com.dh.commerce.services.Exceptions.EntitieNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public CategoriesDTO post(CategoriesDTO categoriesDTO){

        Categories categories = new Categories();
        BeanUtils.copyProperties(categoriesDTO, categories);
        return  new CategoriesDTO(categoriesRepository.save(categories));
    }

    @Transactional
    public List<CategoriesDTO> findAll(){
        return categoriesRepository.findAll().stream().map(CategoriesDTO::new).collect(Collectors.toList());
    }

    public CategoriesDTO findById(Long id){
        Categories categories = categoriesRepository.findById(id).orElse(null);
        if (categories != null){
            return  new CategoriesDTO(categories);
        }

        throw new EntitieNotFoundException("Entity not found!");
    }

    public List<String> categoriesName(){
        return categoriesRepository.categoriesNames();
    }
}
