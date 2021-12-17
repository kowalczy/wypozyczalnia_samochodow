package pl.samochody.wypozyczalnia_samochodow.dao;

public class Filter {
    private String brand;
    private String model;
    private boolean available;

    public Filter(String brand, String model, boolean available) {
        this.brand = brand;
        this.model = model;
        this.available = available;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", available=" + available +
                '}';
    }
}
