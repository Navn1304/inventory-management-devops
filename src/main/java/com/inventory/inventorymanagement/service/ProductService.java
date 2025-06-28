package com.inventory.inventorymanagement.service;

import com.inventory.inventorymanagement.model.Product;
import com.inventory.inventorymanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Read All
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Read by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Update
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
