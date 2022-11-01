package Lesson31.shop_jdbc.services;

import Lesson31.shop_jdbc.models.Receipt;
import Lesson31.shop_jdbc.models.Shop;

import java.util.Date;
import java.util.List;

public interface ReceiptService {
    void createReceipt(Receipt receipt);
    List<Receipt> getReceipts();
    Receipt findReceiptById(int id);
    void updateReceipt(int id, Date addDate, int sellerId);
    void deleteReceipt(int id);

    int getLastReceiptId();
}
