package com.example.services;

public class Service {
    private String category,titleService,location,price,description,phone;

    public Service() {
    }

    public Service(String category, String titleService, String location, String price, String description, String phone) {
        this.category = category;
        this.titleService = titleService;
        this.location = location;
        this.price = price;
        this.description = description;
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public String getTitleService() {
        return titleService;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitleService(String titleService) {
        this.titleService = titleService;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
