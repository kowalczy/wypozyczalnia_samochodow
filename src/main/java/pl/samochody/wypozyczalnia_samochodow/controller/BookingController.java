package pl.samochody.wypozyczalnia_samochodow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.Booking;
import pl.samochody.wypozyczalnia_samochodow.service.BookingNotFoundException;
import pl.samochody.wypozyczalnia_samochodow.service.BookingService;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<Booking> bookingList = (List<Booking>) bookingService.findAll();
        model.addAttribute("bookingList", bookingList);
        return("bookings");
    }

    @PostMapping("/save")
    public String saveBooking(Booking booking, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "The car has been booked succesfully.");
        bookingService.save(booking);
        return "redirect:/cars/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            bookingService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The booking with id: " + id + " has been deleted succesfully.");
        } catch (BookingNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/bookings/all";
    }
}
