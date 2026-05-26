package com.scalar.productservicecapstone.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scalar.productservicecapstone.models.Product;

@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "imageUrl",
        "price",
        "category"
})

public class ProductResponseDto {
    private long id;
    private String name;
    private String description;
    private String imageurl;
    private double price;
    private String category;

    public static ProductResponseDto from(Product product)
    {
        if (product == null){
            return null;
        }
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageurl(product.getImageurl());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory().getName());

        return productResponseDto;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageurl() {
        return this.imageurl;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
