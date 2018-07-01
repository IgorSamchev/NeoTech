import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;


public class TimeStampsCommitAndDeleteTest {

    @Test
    public void testCommitAndDelete() throws Exception {
        TimeStamps.timeStampsList = new ArrayList<>();
        String testUrl = DataBase.url;
        String testUserName = DataBase.userName;
        String testPassword = DataBase.password;
        Connection connection = DriverManager.getConnection(testUrl, testUserName, testPassword);
        Statement statement = connection.createStatement();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            statement.executeUpdate("CREATE DATABASE neobase");
            statement.executeUpdate("USE neobase");
            statement.executeUpdate("CREATE TABLE TimeStamps (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "stamp VARCHAR(19))");
        } catch (ClassNotFoundException | SQLException ignored) {
        }

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("+3")));
        String stringTimeStamp = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(timestamp);
        TimeStamps.timeStampsList.add(stringTimeStamp);

        int checkId = 0;
        statement.executeQuery("USE neobase");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM timestamps");
        while (resultSet.next()) {
            checkId = resultSet.getInt("id");
        }

        String stamp = TimeStamps.timeStampsList.get(0);
        String sql = "INSERT INTO neobase.TimeStamps(stamp)" + " VALUES(?)";
        DataBase.preparedStatement = connection.prepareStatement(sql);
        DataBase.preparedStatement.setString(1, stamp);
        DataBase.preparedStatement.execute();
        TimeStamps.timeStampsList.remove(stamp);
        statement.executeQuery("USE neobase");
        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM timestamps");

        int lastId = 0;
        while (resultSet2.next()) {
            lastId = resultSet2.getInt("id");
        }
        Assert.assertEquals(0, TimeStamps.timeStampsList.size());
        Assert.assertEquals(checkId +1, lastId);
    }
} 
