import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeStampsCreation implements Runnable {

    private void createTimeStamp() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("+3")));
        String stringTimeStamp = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(timestamp);
        TimeStamps.timeStampsList.add(stringTimeStamp);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            createTimeStamp();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
