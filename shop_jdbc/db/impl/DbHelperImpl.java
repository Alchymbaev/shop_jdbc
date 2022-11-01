package Lesson31.shop_jdbc.db.impl;

import Lesson31.shop_jdbc.db.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHelperImpl implements DbHelper {

    @Override
    public PreparedStatement getConnection(String sql) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "uhyhuqape");
            System.out.println(connection);
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе");
        }
    }
}
