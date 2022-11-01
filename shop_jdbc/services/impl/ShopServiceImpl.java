package Lesson31.shop_jdbc.services.impl;

import Lesson31.shop_jdbc.db.DbHelper;
import Lesson31.shop_jdbc.db.impl.DbHelperImpl;
import Lesson31.shop_jdbc.models.Shop;
import Lesson31.shop_jdbc.services.ShopService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopServiceImpl implements ShopService {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public void createShop(Shop shop) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("insert into tb_shop(name, active) values(?, ?)");
            preparedStatement.setString(1,shop.getName());
            preparedStatement.setInt(2,shop.getActive());
            preparedStatement.executeUpdate();
            System.out.println("Магазин успешно добавлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка внесения нового магазина");
        }
    }

    @Override
    public List<Shop> getShops() {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, name, active from tb_shop");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Shop> shops = new ArrayList<>();
            while (resultSet.next()){
                Shop shop = new Shop(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("active"));
                shops.add(shop);
            }
            return shops;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода всех магазинов");
        }
    }

    @Override
    public Shop findShopById(int id) {
        Shop shop = null;
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, name, active from tb_shop where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                shop = new Shop(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("active"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода магазина по id");
        }
        return shop;
    }

    @Override
    public void updateShop(int id, String name, int active) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("update tb_shop set name = ?, active = ? where id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, active);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Магазин успешно изменен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка изменения магазина");
        }
    }

    @Override
    public void deleteShop(int id) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("delete from tb_shop where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Магазин успешно удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления магазина");
        }
    }
}
