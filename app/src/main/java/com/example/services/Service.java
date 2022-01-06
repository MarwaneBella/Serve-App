package com.example.services;

public class Service {
    private String username,uimage,category,titleService,location,price,description,phone;

    public Service() {
    }

    public Service(String username, String uimage, String category, String titleService, String location, String price, String description, String phone) {
        this.username = username;
        this.uimage = uimage;
        this.category = category;
        this.titleService = titleService;
        this.location = location;
        this.price = price;
        this.description = description;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitleService() {
        return titleService;
    }

    public void setTitleService(String titleService) {
        this.titleService = titleService;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
