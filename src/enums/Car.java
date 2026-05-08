package enums;

public enum Car {
    COBALT(CarClass.COMFORT),
    SPARK(CarClass.STANDARD),
    MALIBU(CarClass.BUSINESS),
    JENTRA(CarClass.COMFORT),
    KIA5(CarClass.BUSINESS);

    private final CarClass carClass;
    Car(CarClass carClass) {
        this.carClass = carClass;
    }
}
