import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class TimeStampsCreationTest {
    @Test
    public void testCreateTimeStamp() throws Exception {
        TimeStamps.timeStampsList = new ArrayList<>();
        int size = TimeStamps.timeStampsList.size();

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("+3")));
        String stringTimeStamp = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(timestamp);
        TimeStamps.timeStampsList.add(stringTimeStamp);
        int varCharCount = stringTimeStamp.length();

        Assert.assertEquals(1, size + 1);
        Assert.assertEquals(19, varCharCount);
    }
} 
