package pl.samochody.wypozyczalnia_samochodow.service;

public class BookingNotFoundException extends Throwable {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
