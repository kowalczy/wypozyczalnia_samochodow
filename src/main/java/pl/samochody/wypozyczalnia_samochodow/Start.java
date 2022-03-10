package pl.samochody.wypozyczalnia_samochodow;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.samochody.wypozyczalnia_samochodow.dao.repositories.AppUserRepo;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.AppUser;

@Configuration
public class Start {

    private AppUserRepo appUserRepo;

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;

        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole("ROLE_ADMIN");
        appUserRepo.save(admin);
        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRole("ROLE_USER");
        appUserRepo.save(user);

    }
}
