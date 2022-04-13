package com.dh.commerce.dto;

import com.dh.commerce.entities.Categories;
import com.dh.commerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {

    private Long id;
    private Double price;
    private String title;
    private String description;
    private String image;
    private Categories category;

    public ProductDTO(Product product) {
        id = product.getId();
        price = product.getPrice();
        title = product.getTitle();
        description = product.getDescription();
        image = product.getImage();
        category = product.getCategory();
    }
}
