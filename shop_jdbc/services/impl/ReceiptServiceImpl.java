package Lesson31.shop_jdbc.services.impl;

import Lesson31.shop_jdbc.db.DbHelper;
import Lesson31.shop_jdbc.db.impl.DbHelperImpl;
import Lesson31.shop_jdbc.models.Receipt;
import Lesson31.shop_jdbc.models.Shop;
import Lesson31.shop_jdbc.services.ReceiptService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceiptServiceImpl implements ReceiptService {
    DbHelper dbHelper = new DbHelperImpl();
    @Override
    public void createReceipt(Receipt receipt) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("insert into tb_receipt(add_date, seller_id) values(?, ?)");
            preparedStatement.setDate(1, new java.sql.Date(receipt.getAddDate().getTime()));
            preparedStatement.setInt(2, receipt.getSellerId());
            preparedStatement.executeUpdate();
            System.out.println("Чек успешно добавлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка внесения нового чека");
        }
    }

    @Override
    public List<Receipt> getReceipts() {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, add_date, seller_id from tb_receipt");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Receipt> receipts = new ArrayList<>();
            while (resultSet.next()){
                Receipt receipt = new Receipt(resultSet.getInt("id"), resultSet.getDate("add_date"), resultSet.getInt("seller_id"));
                receipts.add(receipt);
            }
            return receipts;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода всех чеков");
        }
    }

    @Override
    public Receipt findReceiptById(int id) {
        Receipt receipt = null;
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, add_date, seller_id from tb_receipt where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                receipt = new Receipt(resultSet.getInt("id"), resultSet.getDate("add_date"), resultSet.getInt("seller_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода чека по id");
        }
        return receipt;
    }

    @Override
    public void updateReceipt(int id, Date addDate, int sellerId) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("update tb_receipt set add_date = ?, seller_id = ? where id = ?");
            preparedStatement.setDate(1, new java.sql.Date(addDate.getTime()));
            preparedStatement.setInt(2, sellerId);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Чек успешно изменен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка изменения чека");
        }
    }

    @Override
    public void deleteReceipt(int id) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("delete from tb_receipt where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Чек успешно удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления чека");
        }
    }

    @Override
    public int getLastReceiptId() {
        int receiptId = 0;
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select MAX(id) from tb_receipt");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                receiptId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода последнего чека");
        }
        return receiptId;
    }
}
