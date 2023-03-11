package com.shop.library.repository;

import com.shop.library.dto.CategoryDto;
import com.shop.library.model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query ("select c from Category c where c.status = 'ACTIVATED'")
    List<Category> findAllByActivated();

    @Query ("select new com.shop.library.dto.CategoryDto(c.id,c.name,c.status) "
            + "from Category c inner join Product p on p.category.id = c.id "
            + "where c.status = 'ACTIVATED' group by c.id")
    List<CategoryDto> getCategoryAndProduct();
}
