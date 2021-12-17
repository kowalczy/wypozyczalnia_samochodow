package pl.samochody.wypozyczalnia_samochodow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.AppUser;
import pl.samochody.wypozyczalnia_samochodow.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String signup(Model model){
        model.addAttribute("user", new AppUser());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser, RedirectAttributes redirectAttributes){
        if(appUser.getUsername() != "" && appUser.getPassword() != ""){
            for(AppUser user : userService.getAllUsers()){
                if(user.getUsername().equals(appUser.getUsername())){
                    redirectAttributes.addFlashAttribute("message", "Invalid username! There is such user in a database already!");
                    return "redirect:/sign-up";
                }
            }
                userService.addUser(appUser);
                return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("message", "Invalid username or password!");
        return "redirect:/sign-up";
    }
}
