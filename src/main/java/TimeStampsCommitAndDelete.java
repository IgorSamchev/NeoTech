import java.sql.SQLException;


public class TimeStampsCommitAndDelete implements Runnable {

    private void commitAndDelete() {
        if (TimeStamps.timeStampsList.size() > 0) {
                String stamp = TimeStamps.timeStampsList.get(0);
                String sql = "INSERT INTO neobase.TimeStamps(stamp)" + " VALUES(?)";
                try {
                    DataBase.preparedStatement = DataBase.connection.prepareStatement(sql);
                    DataBase.preparedStatement.setString(1, stamp);
                    DataBase.preparedStatement.execute();
                } catch (SQLException e) {
                    System.out.println("Database connection problem. Reconnect in 5 seconds");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    run();
                }
                TimeStamps.timeStampsList.remove(stamp);
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            commitAndDelete();
        }
    }
}
