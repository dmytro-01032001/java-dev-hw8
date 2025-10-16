package hw8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Database INSTANCE = new Database();
    private static Connection connection;

    private Database() {
        String url = PropertyReader.getUrl();
        String user = PropertyReader.getUser();
        String pswd = PropertyReader.getPassword();
        try {
            connection = DriverManager.getConnection(url, user, pswd);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    public static Database getInstance() {
        return INSTANCE;
    }

    public static Connection getConnection() {
        return connection;
    }
}
