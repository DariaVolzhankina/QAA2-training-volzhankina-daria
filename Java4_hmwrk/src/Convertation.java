import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Convertation {

    public static Timestamp convertLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}