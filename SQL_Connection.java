import java.sql.*;

public class SQL_Connection {
    static Connection connection;

    protected static void createSQLConnection() {
        String url = "jdbc:mysql://localhost:3306/Dragline";
        String user = "root";
        String password = "P02122003K";
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to SQL :)");
        } catch (Exception e) {
            System.out.println("Enable to create connection with SQL..!!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    protected static void closeSQLConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
