package pl.samochody.wypozyczalnia_samochodow.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.AppUser;
import pl.samochody.wypozyczalnia_samochodow.dao.repositories.AppUserRepo;

@Service
public class UserService {
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(AppUser appUser) {
        if(appUser.getUsername() == "" || appUser.getPassword() == ""){

        }else{
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appUser.setRole("ROLE_USER");
            appUserRepo.save(appUser);
        }
    }

    public AppUser findUserByUsername(String s) {
        return appUserRepo.findByUsername(s).get(); //TODO: throw if not exist orElseThrow()
    }
    public Iterable<AppUser> getAllUsers(){
        return appUserRepo.findAll();
    }
}
