package com.scalar.productservicecapstone.dtos;


public class CreateFakeStoreProductDto {
    private String name;
    private String description;
    private String category;
    private double price;
    private String imageurl;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    public String getImageurl() {
        return this.imageurl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
