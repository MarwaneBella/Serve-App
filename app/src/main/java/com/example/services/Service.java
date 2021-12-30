package com.example.services;

public class Service {
    public String category,titleService,location,price,description,phone;

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

}
