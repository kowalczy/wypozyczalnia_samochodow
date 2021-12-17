package pl.samochody.wypozyczalnia_samochodow.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String brand;
    private String model;
    private String color;
    private int horsepower;
    private boolean available;

    public Car(){
    }

    public Car(Long id, String brand, String model, String color, int horsepower) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.horsepower = horsepower;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
