package Lesson31.shop_jdbc.services;

import Lesson31.shop_jdbc.models.Shop;

import java.util.List;

public interface ShopService {
    void createShop(Shop shop);
    List<Shop> getShops();
    Shop findShopById(int id);
    void updateShop(int id, String name, int active);
    void deleteShop(int id);
}
