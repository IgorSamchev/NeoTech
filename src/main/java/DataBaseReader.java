import java.sql.DriverManager;
import java.sql.ResultSet;

class DataBaseReader {
    static void readDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DataBase.connection = DriverManager.getConnection(DataBase.url, DataBase.userName, DataBase.password);
            DataBase.statement = DataBase.connection.createStatement();
            DataBase.statement.executeQuery("USE neobase");
            ResultSet resultSet = DataBase.statement.executeQuery("SELECT * FROM timestamps");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String timestamps = resultSet.getString("stamp");
                System.out.format("%s, %s", id, timestamps);
                System.out.println();
            }
            DataBase.statement.close();
            DataBase.connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
