package com.example.moonlighthotel.dto.transfer;

import java.util.Set;

public class CarResponse {

    private Long id;
    private String brand;
    private String model;
    private String image;
    private Set<String> images;
    private int year;
    private String created;
    private CarCategoryResponse category;

    public CarResponse() {
    }

    public CarResponse(Long id, String brand, String model, String image, Set<String> images, int year, String created,
                       CarCategoryResponse category) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.image = image;
        this.images = images;
        this.year = year;
        this.created = created;
        this.category = category;
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

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
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

    public CarCategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CarCategoryResponse category) {
        this.category = category;
    }


}
