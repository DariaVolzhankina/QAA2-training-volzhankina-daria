package objects;

import lombok.Builder;
import lombok.Data;
import objects.enums.Currencies;

import java.util.Objects;

@Data
@Builder
public class Cash {
    private int sum;
    private final Currencies currency;

    public Cash(int sum, Currencies currency) {
        this.sum = sum;
        this.currency = Objects.requireNonNull(currency);
    }
}
