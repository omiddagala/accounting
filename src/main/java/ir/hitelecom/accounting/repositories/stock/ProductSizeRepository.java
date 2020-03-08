package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductSizeRepository extends CrudRepository<ProductSize,Long> {

    @Query(value = "select s.id , s.value, ps.count from sizes as s left join product_size as ps on ps.size_id = s.id and ps.product_id = :productId", nativeQuery = true)
    List<Object[]> getSizes(@Param("productId") Long productId);

    void deleteByProduct(Product product);
}
