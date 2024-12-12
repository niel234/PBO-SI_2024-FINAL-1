package libraryApp.congfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String dbName;
    private final String userName;
    private final String password;
    private final String host;
    private final String port;
    private Connection connection;

    public Database(final String dbName, final String userName, final String password, final String host, final String port) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public Connection getConnection() {
        if (connection == null || isConnectionClosed()) {
            setup();
        }
        return connection;
    }

    public void setup() {
        String mysqlConnUrlTemplate = "jdbc:mysql://%s:%s/%s";
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Update driver class ke versi terbaru
            connection = DriverManager.getConnection(
                    String.format(mysqlConnUrlTemplate, host, port, dbName),
                    userName,
                    password
            );
            System.out.println("Database connected!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error setting up the database connection: " + e.getMessage(), e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isConnectionClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking connection state: " + e.getMessage(), e);
        }
    }
}