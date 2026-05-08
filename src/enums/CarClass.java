package enums;

import java.math.BigDecimal;

public enum CarClass {
    COMFORT(BigDecimal.valueOf(1000)),
    STANDARD(BigDecimal.valueOf(500)),
    BUSINESS(BigDecimal.valueOf(1500));
// FIELD
    private final BigDecimal farePerSecond;
    // CONSTRUCTOR
     CarClass(BigDecimal farePerSecond) {
        this.farePerSecond = farePerSecond;
     }

}
