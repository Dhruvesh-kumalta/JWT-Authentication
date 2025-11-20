package com.product.in.Sevice.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.in.Repository.ProductRepository;
import com.product.in.Sevice.ProductService;
import com.product.in.entity.Product;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product existing = repo.findById(id).orElseThrow();
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
       
        return repo.save(existing);
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public void deleteProduct(long id) {
        repo.deleteById(id);
    }
}
