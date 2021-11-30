package pl.samochody.wypozyczalnia_samochodow.api;

import org.springframework.web.bind.annotation.*;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;
import pl.samochody.wypozyczalnia_samochodow.manager.CarManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private CarManager carManager;

    public CarApi(CarManager carManager) {
        this.carManager = carManager;
    }

    @GetMapping("/all")
    public Iterable<Car> getAll(){
        return carManager.findAll();
    }

    @GetMapping
    public Optional<Car> getById(@RequestParam Long id){
        return carManager.findById(id);
    }

    @PostMapping
    public Car addCar(@RequestBody Car car){
        return carManager.save(car);
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car){
        return carManager.save(car);
    }

    @DeleteMapping
    public void deleteCar(@RequestParam Long index){
        carManager.deleteById(index);
    }
}
