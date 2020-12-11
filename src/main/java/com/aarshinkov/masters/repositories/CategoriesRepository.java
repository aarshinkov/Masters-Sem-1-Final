package com.aarshinkov.masters.repositories;

import com.aarshinkov.masters.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByCategoryId(Long categoryId);
}
