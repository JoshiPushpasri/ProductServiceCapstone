package com.scalar.productservicecapstone.controllers;

import com.scalar.productservicecapstone.dtos.ProductResponseDto;
import com.scalar.productservicecapstone.exceptions.ProductNotFoundException;
import com.scalar.productservicecapstone.models.Category;
import com.scalar.productservicecapstone.models.Product;
import com.scalar.productservicecapstone.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest
{
    @MockitoBean
    @Qualifier("productDBService")
    public ProductService productService;

    @Autowired
    public ProductController productController;


    @Test
    public void testProductByIdReturnsProductResponseDto() throws ProductNotFoundException {
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("name");
        dummyProduct.setDescription("description");
        dummyProduct.setPrice(12.7);
        dummyProduct.setImageurl("img.url");

        Category dummyCategory = new Category();
        dummyCategory.setId(1L);
        dummyProduct.setName("category");
        dummyCategory.setDescription("description");

        dummyProduct.setCategory(dummyCategory);

        when(productService.getProductById(1L)).thenReturn(dummyProduct);

        ProductResponseDto productResponseDto = productController.getProductById(1L);

        assertEquals(1L, productResponseDto.getId());
        assertEquals("category", productResponseDto.getName());
        assertEquals("description", productResponseDto.getDescription());
        assertEquals("img.url", productResponseDto.getImageurl());
        assertEquals(12.7, productResponseDto.getPrice());
        assertEquals(1L, dummyCategory.getId());

    }

    @Test
    public void testGetProductByIdReturnsNull() throws ProductNotFoundException {
        when(productService.getProductById(1L)).thenReturn(null);

        ProductResponseDto productResponseDto = productController.getProductById(1L);
        assertNull(productResponseDto);

    }

}