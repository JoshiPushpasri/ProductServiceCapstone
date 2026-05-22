package com.scalar.productservicecapstone.controllers;

import com.scalar.productservicecapstone.dtos.CreateFakeStoreProductDto;
import com.scalar.productservicecapstone.dtos.ErrorDto;
import com.scalar.productservicecapstone.dtos.ProductResponseDto;
import com.scalar.productservicecapstone.exceptions.ProductNotFoundException;
import com.scalar.productservicecapstone.models.Product;
import com.scalar.productservicecapstone.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController
{

    ProductService productService;

    public ProductController(@Qualifier("productDBService") ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") long id) throws ProductNotFoundException
    {
        Product product = productService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        //List<ProductResponseDto> productResponseDtos =products.stream().map(ProductResponseDto::from).collect(Collectors.toList());

        for (Product product : products)
        {
            ProductResponseDto productResponseDto = ProductResponseDto.from(product);
            productResponseDtos.add(productResponseDto);
        }

        return  productResponseDtos;
    }

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody CreateFakeStoreProductDto createFakeStoreProductDto)
    {
        Product product = productService.createProduct(
                createFakeStoreProductDto.getName(),
                createFakeStoreProductDto.getDescription(),
                createFakeStoreProductDto.getPrice(),
                createFakeStoreProductDto.getImageurl(),
                createFakeStoreProductDto.getCategory()
                );
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        return productResponseDto;
    }


}
