package com.example.product.service;

import com.example.product.exceptions.ResourceNotFoundException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductProducer productProducer;

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        productProducer.sendMessage("Product Created: " + savedProduct.getId() + ", Name: " + savedProduct.getName());
        return savedProduct;
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        Product updatedProduct = productRepository.save(product);
        productProducer.sendMessage("Product Updated: " + updatedProduct.getId() + ", Name: " + updatedProduct.getName());
        return updatedProduct;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

//    public Product createProduct(Product product) {
//        return productRepository.save(product);
//    }

//    public Product updateProduct(Long id, Product productDetails) {
//        Product product = getProductById(id);
//        product.setName(productDetails.getName());
//        product.setPrice(productDetails.getPrice());
//        product.setStock(productDetails.getStock());
//        return productRepository.save(product);
//    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsSortedByStock() {
        return productRepository.findAllByOrderByStock();
    }

    // Method to get products sorted by price
    public List<Product> getProductsSortedByPrice() {
        return productRepository.findAllByOrderByPrice();
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }
}

