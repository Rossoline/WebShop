package com.shop.admin.lib.repository;

import com.shop.admin.lib.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query ("select p from Product p")
    Page<Product> pageProduct(Pageable pageable);

    @Query ("select p from Product p "
            + "where p.description "
            + "like %?1% or p.name like %?1%")
    Page<Product> searchProducts(String keyword, Pageable pageable);

    @Query ("select p from Product p "
            + "where p.description "
            + "like %?1% or p.name like %?1%")
    List<Product> searchProductsList(String keyword);

    @Query ("select p "
            + "from Product p "
            + "where p.status = 'ACTIVATED'")
    List<Product> getAllProducts();

    @Query (value = "select * "
            + "from products p"
            + " where p.status = 'ACTIVATED' "
            + "order by rand() asc limit 4 ",
            nativeQuery = true)
    List<Product> listViewProducts();

    @Query (value = "select * from products p "
            + "inner join categories c on c.category_id = p.category_id "
            + "where p.category_id = ?1",
            nativeQuery = true)
    List<Product> getRelatedProducts(Long categoryId);

    @Query (value = "select p from Product p "
            + "inner join Category c on c.id=p.category.id "
            + "where c.id = ?1 and p.status = 'ACTIVATED'")
    List<Product> getProductsInCategory(Long categoryId);

    @Query ("select p from Product p "
            + "where p.status = 'ACTIVATED' "
            + "order by p.costPrice desc")
    List<Product> filterHighPrice();

    @Query ("select p from Product p "
            + "where p.status = 'ACTIVATED' "
            + "order by p.costPrice asc")
    List<Product> filterLowerPrice();

    List<Product> findAll(Sort sort);
}
