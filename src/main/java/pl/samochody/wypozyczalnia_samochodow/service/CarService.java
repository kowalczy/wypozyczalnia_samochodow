package pl.samochody.wypozyczalnia_samochodow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.samochody.wypozyczalnia_samochodow.dao.repositories.CarRepo;
import pl.samochody.wypozyczalnia_samochodow.dao.Filter;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car findById(Long id) throws CarNotFoundException {
        Optional<Car> result = carRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new CarNotFoundException("Could not find Car with id: " + id);

    }

    public Iterable<Car> findAll(){
        return carRepo.findAll();
    }

    public Car save(Car car){
        return carRepo.save(car);
    }

    public void deleteById(Long id) throws CarNotFoundException {
        Optional<Car> result = carRepo.findById(id);
        if(result.isPresent()){
            carRepo.deleteById(id);
        }else {
            throw new CarNotFoundException("There is no car with id: " + id);
        }
    }

    public Iterable<Car> findByBrand(String brand){
        return carRepo.findByBrand(brand);
    }

    public Iterable<Car> findByAvailable(boolean available){
        return carRepo.findByAvailable(available);
    }

    public Iterable<Car> filter(Filter filter){
        List<Car> list = (List<Car>) carRepo.findAll();
        List<Car> result = new ArrayList<>();
        for(Car c : list) {
            if(filter.isAvailable()){
                if(filter.getBrand() != ""){
                    if(filter.getModel() != ""){
                        if(c.getAvailable() && c.getBrand().equals(filter.getBrand()) && c.getModel().equals(filter.getModel())){ // filter po 3 polach
                            result.add(c);
                        }
                    }else{
                       if(c.getAvailable() && c.getBrand().equals(filter.getBrand())){ //filter po available i brand
                           result.add(c);
                       }
                    }
                }else{
                    if(filter.getModel() != ""){
                        if(c.getAvailable() && c.getModel().equals(filter.getModel())){ //filter po available i model
                            result.add(c);
                        }
                    }else{
                        if(c.getAvailable())
                            result.add(c); //filter po available
                    }
                }
            }else{ //available is false
                if(filter.getBrand() != ""){
                    if(filter.getModel() != ""){
                        if(c.getBrand().equals(filter.getBrand()) && c.getModel().equals(filter.getModel())){ //filter po brand i model
                            result.add(c);
                        }
                    }else{
                        if(c.getBrand().equals(filter.getBrand())){ //filter po brand
                            result.add(c);
                        }
                    }
                }else{
                    if(filter.getModel() != ""){
                        if(c.getModel().equals(filter.getModel())){ // filter po model
                            result.add(c);
                        }
                    }
                }
            }
        }
        return result;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Car(1L, "Opel", "Astra", "srebrny", 95));
        save(new Car(2L, "Mercedes", "Fajny", "biały", 190));
    }
}
