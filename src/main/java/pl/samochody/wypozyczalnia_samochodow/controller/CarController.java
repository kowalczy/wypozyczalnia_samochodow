package pl.samochody.wypozyczalnia_samochodow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.samochody.wypozyczalnia_samochodow.dao.Filter;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Booking;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Car;
import pl.samochody.wypozyczalnia_samochodow.service.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;
    private UserService userService;
    private BookingService bookingService;
    public CarController(CarService carService, UserService userService, BookingService bookingService) {
        this.carService = carService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/cars/all")
    public String getAll(Model model, Principal principal){
        List<Car> carList = (List<Car>) carService.findAll();
        model.addAttribute("carList", carList);
        model.addAttribute("filter", new Filter("","",false));
        if(userService.findUserByUsername(principal.getName()).getRole() == "ROLE_USER"){
            return "cars_user";
        }else{
            return "cars_admin";
        }
    }

    @PostMapping("/cars/filter")
    public String filter(Filter filter, Model model, Principal principal){
        List<Car> carList;
        if(filter.isAvailable() || (filter.getBrand() != "" && filter.getBrand() != null) || (filter.getModel() != "" && filter.getModel() != null)){
            carList = (List<Car>) carService.filter(filter);
        }else{
            carList = (List<Car>) carService.findAll();
        }
        model.addAttribute("carList", carList);
        if(userService.findUserByUsername(principal.getName()).getRole() == "ROLE_USER"){
            return "cars_user";
        }else{
            return "cars_admin";
        }
    }

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/cars/new")
    public String showNewCarForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("pageTitle", "Add new Car");
        return "car_form";
    }

    @PostMapping("/cars/save")
    public String saveCar(Car car, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "The Car has been saved succesfully.");
        carService.save(car);
        return "redirect:/cars/all";
    }

    @GetMapping("/cars/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        try {
            Car car = carService.findById(id);
            model.addAttribute("car", car);
            model.addAttribute("pageTitle", "Edit Car");
            return "car_form";
        } catch (CarNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/cars/all";
        }
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            Car car = carService.findById(id);
            List<Booking> bookingList = (List<Booking>) bookingService.findByCar(car);
            if(!car.getAvailable() && bookingList.size() > 0){
                bookingService.deleteById(((List<Booking>) bookingService.findByCar(car)).get(0).getId());
            }
            carService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The car with id: " + id + " has been deleted succesfully.");
        } catch (CarNotFoundException | BookingNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cars/all";
    }

    @GetMapping("/cars/book/{id}")
    public String bookCar(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal principal){
        try {
            Car car = carService.findById(id);
            if(car.getAvailable()){
                Booking booking = new Booking(0L, LocalDateTime.now(), car, userService.findUserByUsername(principal.getName()));
                bookingService.save(booking);
                car.setAvailable(false);
                carService.save(car);
                redirectAttributes.addFlashAttribute("message", "The car with id: " + id + " has been booked succesfully.");
            }else {
                redirectAttributes.addFlashAttribute("message", "You can not book unavailable car!");
            }
        } catch (CarNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cars/all";
    }

    @GetMapping("/cars/return/{id}")
    public String returnCar(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal principal){
        try {
            Car car = carService.findById(id);
            List<Booking> bookingList = (List<Booking>) bookingService.findByCar(car);
            if(!car.getAvailable() && bookingList.size() > 0){
                if(userService.findUserByUsername(principal.getName()) == bookingList.get(0).getAppUser()){
                    car.setAvailable(true);
                    carService.save(car);
                    bookingService.deleteById(bookingList.get(0).getId());
                    redirectAttributes.addFlashAttribute("message", "The Car with id: " + id + " has been returned succesfully.");
                }else{
                    redirectAttributes.addFlashAttribute("message", "You can not return other user's Car!");
                }

            }else {
                redirectAttributes.addFlashAttribute("message", "You can not return unbooked ar!");
            }
        } catch (CarNotFoundException | BookingNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cars/all";
    }
}
