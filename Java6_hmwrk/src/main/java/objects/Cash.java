package objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cash {
    private int sum;
    private final Currencies currency;

    public Cash(int sum, Currencies currency) {
        this.sum = sum;
        this.currency = currency;
    }
}
