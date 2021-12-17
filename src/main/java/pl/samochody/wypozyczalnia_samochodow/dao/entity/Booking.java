package pl.samochody.wypozyczalnia_samochodow.dao.entity;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "bookings")
public class Booking {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDateTime dateTime;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public Booking(){
    }

    public Booking(Long id, LocalDateTime dateTime, Car car, AppUser appUser) {
        this.id = id;
        this.dateTime = dateTime;
        this.car = car;
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public Car getCar() {
        return car;
    }

    public Long getCarId(){
        return car.getId();
    }
    public Long getAppUserId(){
        return appUser.getId();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        String date = dateTime.getDayOfMonth() + "." + dateTime.getMonthValue() + "." + dateTime.getYear();
        return date;
    }

    public String getTime(){
        String time = dateTime.getHour() + ":" + dateTime.getMinute() + ":" + dateTime.getSecond();
        return time;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
