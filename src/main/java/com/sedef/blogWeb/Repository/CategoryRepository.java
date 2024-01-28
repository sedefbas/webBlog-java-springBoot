package com.sedef.blogWeb.Repository;

import com.sedef.blogWeb.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
