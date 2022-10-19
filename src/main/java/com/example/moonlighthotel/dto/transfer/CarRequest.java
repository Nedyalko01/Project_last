package com.example.moonlighthotel.dto.transfer;

import java.util.Set;

public class CarRequest {

    private Long category;
    private String brand;
    private String model;
    private String image;
    private Set<String> images;
    private int year;

    public CarRequest() {
    }

    public CarRequest(Long category, String brand, String model, String image, Set<String> images, int year) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.image = image;
        this.images = images;
        this.year = year;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
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
}
