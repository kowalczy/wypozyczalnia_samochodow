package pl.samochody.wypozyczalnia_samochodow.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.samochody.wypozyczalnia_samochodow.dao.repositories.AppUserRepo;
import pl.samochody.wypozyczalnia_samochodow.dao.entity.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepo appUserRepo;

    public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public AppUser loadUserByUsername(String s) throws UsernameNotFoundException{
        return appUserRepo.findByUsername(s).get(); //TODO: throw if not exist orElseThrow()
    }
}
