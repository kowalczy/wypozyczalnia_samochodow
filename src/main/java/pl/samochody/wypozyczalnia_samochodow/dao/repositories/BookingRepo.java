package pl.samochody.wypozyczalnia_samochodow.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Booking;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;

public interface BookingRepo extends CrudRepository<Booking, Long> {
    public Iterable<Booking> findByCar(Car car);
}
