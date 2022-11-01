package Lesson31.shop_jdbc.services;

import Lesson31.shop_jdbc.models.ProductCount;

import java.util.ArrayList;

public interface SellOperationService {
    void sellProducts();

    void printCheck(ArrayList<ProductCount> products, int shopId, int sellerId);

    void insertCustomerProducts(ArrayList<ProductCount> products);
}
