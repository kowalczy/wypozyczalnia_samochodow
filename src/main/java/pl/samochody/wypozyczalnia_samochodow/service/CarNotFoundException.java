package pl.samochody.wypozyczalnia_samochodow.service;

public class CarNotFoundException extends Throwable {
    public CarNotFoundException(String message) {
        super(message);
    }
}
