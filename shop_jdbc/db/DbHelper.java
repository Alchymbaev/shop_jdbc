package Lesson31.shop_jdbc.db;

import java.sql.PreparedStatement;

public interface DbHelper {
    PreparedStatement getConnection(String sql);
}
