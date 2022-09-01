package objects;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import objects.enums.Currencies;

@Data
@Builder
public class Cash {
    private final Currencies currency;
    private int sum;

    public Cash(int sum, @NonNull Currencies currency) {
        this.sum = sum;
        this.currency = currency;
    }
}
