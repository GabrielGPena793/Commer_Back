package com.dh.commerce.dto;

import com.dh.commerce.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoriesDTO {

    private Long id;
    private String name;

    public CategoriesDTO(Categories categories) {
        id = categories.getId();
        name = categories.getName();
    }
}
