package com.scalar.productservicecapstone.repositories;

import com.scalar.productservicecapstone.models.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    @EntityGraph(attributePaths = "products")

    Optional<Category> findByName(String name);

    Category save(Category category);
}
