package pl.samochody.wypozyczalnia_samochodow.dao;

import org.springframework.data.repository.CrudRepository;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;

public interface CarRepo extends CrudRepository<Car, Long> {
}
