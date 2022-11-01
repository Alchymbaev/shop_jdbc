package Lesson31.shop_jdbc.services;

import Lesson31.shop_jdbc.models.Seller;
import Lesson31.shop_jdbc.models.Shop;

import java.util.List;

public interface SellerService {
    void createSeller(Seller seller);
    List<Seller> getSellers();
    Seller findSellerById(int id);
    void updateSeller(int id, String name, int age, int active, int shopId);
    void deleteSeller(int id);
    List<Seller> findSellersByShopId(int shopId);
}
