package Lesson31.shop_jdbc.services.impl;

import Lesson31.shop_jdbc.db.DbHelper;
import Lesson31.shop_jdbc.db.impl.DbHelperImpl;
import Lesson31.shop_jdbc.models.Seller;
import Lesson31.shop_jdbc.services.SellerService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerServiceImpl implements SellerService {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public void createSeller(Seller seller) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("insert into tb_seller(name, age, active, shop_id) values (?, ?, ? ,?)");
            preparedStatement.setString(1,seller.getName());
            preparedStatement.setInt(2,seller.getAge());
            preparedStatement.setInt(3,seller.getActive());
            preparedStatement.setInt(4,seller.getShopId());
            preparedStatement.executeUpdate();
            System.out.println("Сотрудник успешно добавлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка внесения нового продавца");
        }
    }

    @Override
    public List<Seller> getSellers() {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, name, age, active, shop_id from tb_seller");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Seller> sellers = new ArrayList<>();
            while (resultSet.next()){
                Seller seller = new Seller(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getInt("active"), resultSet.getInt("shop_id"));
                sellers.add(seller);
            }
            return sellers;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода всех сотрудников");
        }
    }

    @Override
    public Seller findSellerById(int id) {
        Seller seller = null;
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, name, age, active, shop_id from tb_seller where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                seller = new Seller(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getInt("active"), resultSet.getInt("shop_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода сотрудника по id");
        }
        return seller;
    }

    @Override
    public void updateSeller(int id, String name, int age, int active, int shopId) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("update tb_seller set name = ?, age = ?, active = ?, shop_id = ? where id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, active);
            preparedStatement.setInt(4, shopId);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Сотрудник успешно изменен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка изменения сотрудника");
        }
    }

    @Override
    public void deleteSeller(int id) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("delete from tb_seller where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Сотрудник успешно удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления сотрудника");
        }
    }

    @Override
    public List<Seller> findSellersByShopId(int shopId) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, name, age, active, shop_id from tb_seller where shop_id = ?");
            preparedStatement.setInt(1, shopId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Seller> sellers = new ArrayList<>();
            while (resultSet.next()){
                Seller seller = new Seller(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getInt("active"), resultSet.getInt("shop_id"));
                sellers.add(seller);
            }
            return sellers;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода сотрудников по id магазина");
        }
    }
}
