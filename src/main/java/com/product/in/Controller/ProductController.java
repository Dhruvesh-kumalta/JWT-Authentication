package com.product.in.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.product.in.Sevice.ProductService;
import com.product.in.entity.Product;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public Product add(@RequestBody Product p) {
        return service.addProduct(p);
    }

    @PutMapping("/update/{id}")
    public Product update(@PathVariable long id, @RequestBody Product p) {
        return service.updateProduct(id, p);
    }

    @GetMapping("/all")
    public List<Product> all() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        return service.getProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        service.deleteProduct(id);
        return "Product Deleted Successfully";
    }
}
