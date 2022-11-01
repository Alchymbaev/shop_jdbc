package Lesson31.shop_jdbc.services;

import Lesson31.shop_jdbc.models.ProductReceipt;
import Lesson31.shop_jdbc.models.Receipt;
import Lesson31.shop_jdbc.models.Shop;

import java.util.List;

public interface ProductReceiptService {
    void createReceiptProduct(ProductReceipt productReceipt);
    List<ProductReceipt> getReceiptProducts();
    List<ProductReceipt> getReceiptProductsByReceiptId(int receiptId);
    void updateReceiptProduct(int id, int receiptId, int productId);
    void deleteReceiptProduct(int id);
}
