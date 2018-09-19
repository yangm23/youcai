package com.youcai.user.service;



import com.youcai.user.dataobject.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    /**
     * @param code
     * @return
     */
    Category findOne(String code);

    List<Category> findAll();

    Map<String, String> findAllInMap();
}
