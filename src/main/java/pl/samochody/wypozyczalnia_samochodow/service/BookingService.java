package pl.samochody.wypozyczalnia_samochodow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Booking;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;
import pl.samochody.wypozyczalnia_samochodow.dao.repositories.BookingRepo;

import java.util.Optional;

@Service
public class BookingService {
    private BookingRepo bookingRepo;

    @Autowired
    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public Booking findById(Long id) throws BookingNotFoundException {
        Optional<Booking> result = bookingRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new BookingNotFoundException("Could not find Car with id: " + id);

    }

    public Iterable<Booking> findAll(){
        return bookingRepo.findAll();
    }

    public Booking save(Booking booking){
        return bookingRepo.save(booking);
    }

    public void deleteById(Long id) throws BookingNotFoundException {
        Optional<Booking> result = bookingRepo.findById(id);
        if(result.isPresent()){
            bookingRepo.deleteById(id);
        }else {
            throw new BookingNotFoundException("There is no booking with id: " + id);
        }
    }

    public Iterable<Booking> findByCar(Car car){
        return bookingRepo.findByCar(car);
    }
}
