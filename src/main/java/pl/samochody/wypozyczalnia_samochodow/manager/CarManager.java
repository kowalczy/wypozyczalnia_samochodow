package pl.samochody.wypozyczalnia_samochodow.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.samochody.wypozyczalnia_samochodow.dao.CarRepo;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarManager {

    private CarRepo carRepo;

    @Autowired
    public CarManager(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Optional<Car> findById(Long id){
        return carRepo.findById(id);
    }

    public Iterable<Car> findAll(){
        return carRepo.findAll();
    }

    public Car save(Car car){
        return carRepo.save(car);
    }

    public void deleteById(Long id){
        carRepo.deleteById(id);
    }

    public Iterable<Car> findByBrand(String brand){
        return carRepo.findByBrand(brand);
    }

    public Iterable<Car> findByAvailable(boolean available){
        return carRepo.findByAvailable(available);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Car(1L, "Opel", "Astra", "srebrny", 95));
        save(new Car(2L, "Mercedes", "Fajny", "biały", 190));
    }
}
