package com.example.moonlighthotel.model.car;

import com.example.moonlighthotel.model.Image;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Set<Image> images;

    private int year;

    private String created;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CarCategory category;

    @OneToMany(mappedBy = "car")
    private List<CarTransfer> carTransfers;

    public Car() {
    }

    public Car(Long id, String brand, String model, String image, Set<Image> images, int year, String created,
               CarCategory category, List<CarTransfer> carTransfers) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.image = image;
        this.images = images;
        this.year = year;
        this.created = created;
        this.category = category;
        this.carTransfers = carTransfers;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public List<CarTransfer> getCarTransfers() {
        return carTransfers;
    }

    public void setCarTransfers(List<CarTransfer> carTransfers) {
        this.carTransfers = carTransfers;
    }
}
