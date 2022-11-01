package Lesson31.shop_jdbc.services.impl;

import Lesson31.shop_jdbc.db.DbHelper;
import Lesson31.shop_jdbc.db.impl.DbHelperImpl;
import Lesson31.shop_jdbc.models.ProductReceipt;
import Lesson31.shop_jdbc.models.Shop;
import Lesson31.shop_jdbc.services.ProductReceiptService;
import Lesson31.shop_jdbc.services.ProductService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductReceiptServiceImpl implements ProductReceiptService {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public void createReceiptProduct(ProductReceipt productReceipt) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("insert into tb_product_receipt(receipt_id, product_id) values(?, ?)");
            preparedStatement.setInt(1,productReceipt.getReceiptId());
            preparedStatement.setInt(2,productReceipt.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка внесения новой связи чек-продукт");
        }
    }

    @Override
    public List<ProductReceipt> getReceiptProducts() {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, receipt_id, product_id from tb_product_receipt");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductReceipt> productReceipts = new ArrayList<>();
            while (resultSet.next()){
                ProductReceipt productReceipt = new ProductReceipt(resultSet.getInt("id"), resultSet.getInt("receipt_id"), resultSet.getInt("product_id"));
                productReceipts.add(productReceipt);
            }
            return productReceipts;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода всех связей чек-продукт");
        }
    }

    @Override
    public List<ProductReceipt> getReceiptProductsByReceiptId(int receiptId) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, receipt_id, product_id from tb_product_receipt where receipt_id = ?");
            preparedStatement.setInt(1, receiptId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductReceipt> productReceipts = new ArrayList<>();
            while (resultSet.next()){
                ProductReceipt productReceipt = new ProductReceipt(resultSet.getInt("id"), resultSet.getInt("receipt_id"), resultSet.getInt("product_id"));
                productReceipts.add(productReceipt);
            }
            return productReceipts;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода всех связей чек-продукт по id чека");
        }
    }

    @Override
    public void updateReceiptProduct(int id, int receiptId, int productId) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("update tb_product_receipt set receipt_id = ?, product_id = ? where id = ?");
            preparedStatement.setInt(1, receiptId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Связь успешно изменена");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка изменения связик чек-продукт");
        }
    }

    @Override
    public void deleteReceiptProduct(int id) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("delete from tb_product_receipt where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Связь успешно удалена");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления связи чек-продукт");
        }
    }
}
