package pl.samochody.wypozyczalnia_samochodow.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;

public interface CarRepo extends CrudRepository<Car, Long> {
    public Iterable<Car> findByBrand(String brand);
    public Iterable<Car> findByAvailable(boolean available);
}
