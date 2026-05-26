package com.scalar.productservicecapstone.services;

import com.scalar.productservicecapstone.exceptions.ProductNotFoundException;
import com.scalar.productservicecapstone.models.Category;
import com.scalar.productservicecapstone.models.Product;
import com.scalar.productservicecapstone.repositories.CategoryRepository;
import com.scalar.productservicecapstone.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Optional;


@Service("productDBService")
public class ProductDBService implements ProductService {


    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductDBService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        //Optional<Category> categoryOptional = categoryRepository.findByName("Electronics");
        //List<Product> products = categoryOptional.get().getProducts();


        //List<Product> products = productRepository.getProductsByCategoryNameNative("Electronics");
        //System.out.println(products);
        //return null;


        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty())
        {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, String description, double price, String imageurl, String category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageurl(imageurl);

        Category categoryObj = getCategoryFromDB(category);

        product.setCategory(categoryObj);
        return productRepository.save(product);
    }

    private Category getCategoryFromDB(String name)
    {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if(optionalCategory.isPresent())
        {
            return optionalCategory.get();
        }

        Category category = new Category();
        category.setName(name);

        return categoryRepository.save(category);

    }
}