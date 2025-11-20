package com.product.in.Sevice;

import java.util.List;
import com.product.in.entity.Product;

public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(long id, Product product);

    List<Product> getAllProducts();

    Product getProductById(long id);

    void deleteProduct(long id);
}
