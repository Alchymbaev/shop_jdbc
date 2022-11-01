package Lesson31.shop_jdbc.services.impl;

import Lesson31.shop_jdbc.db.DbHelper;
import Lesson31.shop_jdbc.db.impl.DbHelperImpl;
import Lesson31.shop_jdbc.models.Product;
import Lesson31.shop_jdbc.models.Shop;
import Lesson31.shop_jdbc.services.ProductService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    DbHelper dbHelper = new DbHelperImpl();
    @Override
    public void createProduct(Product product) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("insert into tb_product(name, price) values(?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("Продукт успешно добавлен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка внесения нового продукта");
        }
    }

    @Override
    public List<Product> getProducts() {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, name, price from tb_product");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода всех продуктов");
        }
    }

    @Override
    public Product findProductById(int id) {
        Product product = null;
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("select id, name, price from tb_product where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вывода продукта по id");
        }
        return product;
    }

    @Override
    public void updateProduct(int id, String name, double price) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("update tb_product set name = ?, price = ? where id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Продукт успешно изменен");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка изменения продукта");
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            PreparedStatement preparedStatement = dbHelper.getConnection("delete from tb_product where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Продукт успешно удален");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления продукта");
        }
    }
}
