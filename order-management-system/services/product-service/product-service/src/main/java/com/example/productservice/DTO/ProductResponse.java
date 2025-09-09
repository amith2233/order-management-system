package com.example.productservice.DTO;

import com.example.productservice.model.Product;

public record ProductResponse (
    Long id,
    String name,
    String description,
    String price,
    String category,
    String company
){
    public ProductResponse(Product product){
        this(product.getId(), product.getName(), product.getDescription(),"$"+String.format("%.2f",product.getPrice()), product.getCategory(), product.getCompany());

    }
}
