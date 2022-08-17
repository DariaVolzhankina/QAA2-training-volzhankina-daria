import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cash {
    private int sum;
    private String currency;

    public Cash(int sum, String currency) {
        this.sum = sum;
        this.currency = currency;
    }
}
