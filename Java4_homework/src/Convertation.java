import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Convertation {
    public static Timestamp convertLocalDateTimeToTimestamp(LocalDateTime localDateTime){
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        System.out.println(timestamp.getNanos()/1000);
        return timestamp;
    }
}
