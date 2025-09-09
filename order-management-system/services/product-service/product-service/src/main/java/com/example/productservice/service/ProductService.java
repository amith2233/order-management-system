package com.example.productservice.service;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long id, Product newProduct){
        Product product = getProductById(id);
        if(newProduct.getName()!=null) product.setName(newProduct.getName());
        if(newProduct.getPrice()!=null) product.setPrice(newProduct.getPrice());
        if(newProduct.getDescription()!=null) product.setDescription(newProduct.getDescription());
        if(newProduct.getCategory()!=null) product.setCategory(newProduct.getCategory());
        if(newProduct.getCompany()!=null) product.setCompany(newProduct.getCompany());
        return productRepository.save(product);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();

    }




}
