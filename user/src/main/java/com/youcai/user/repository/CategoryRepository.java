package com.youcai.user.repository;


import com.youcai.user.dataobject.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

}

