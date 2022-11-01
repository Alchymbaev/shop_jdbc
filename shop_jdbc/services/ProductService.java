package Lesson31.shop_jdbc.services;

import Lesson31.shop_jdbc.models.Product;

import java.util.List;

public interface ProductService {
    void createProduct(Product product);
    List<Product> getProducts();
    Product findProductById(int id);
    void updateProduct(int id, String name, double price);
    void deleteProduct(int id);
}
