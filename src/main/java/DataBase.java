import java.sql.*;


class DataBase {
    static PreparedStatement preparedStatement;
    static Connection connection;
    static Statement statement;
    static String url = "jdbc:mysql://localhost:3306?verifyServerCertificate=false&useSSL=true&useUnicode=true";
    static String userName = "root";
    static String password = "";

    static void createDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE neobase");
            statement.executeUpdate("USE neobase");
            statement.executeUpdate("CREATE TABLE TimeStamps (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "stamp VARCHAR(19))");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
