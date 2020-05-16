package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.entities.stock.Reservoir;
import ir.hitelecom.accounting.entities.stock.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductSizeRepository extends CrudRepository<ProductSize, Long> {

    @Query(value = "select s.id , s.value, ps.count, ps.id as psid, re.name, pr.id as prid, re.id as reid, ps.code from sizes as s left join product_size as ps on ps.size_id = s.id and ps.product_id = :productId join product as pr on ps.product_id = pr.id join reservoir re on pr.reservoir_id = re.id", nativeQuery = true)
    List<Object[]> getSizes(@Param("productId") Long productId);

    void deleteByProduct(Product product);

    void deleteBySize(Size size);

    ProductSize findByProductReservoirAndProductNameAndSizeId(Reservoir reservoir, String name, Long id);

    List<ProductSize> findProductByProductNameAndProductOwner(String name, User owner);

    @Query(value = "from ProductSize ps where ( ps.product.reservoir = :reservoir) and (ps.code= :code)")
    ProductSize findByReservoirAndCode(@Param("reservoir") Reservoir reservoir, @Param("code") Long code);

    @Query(value = "from ProductSize ps where ( ps.product.reservoir = :reservoir) and (ps.id= :id)")
    ProductSize findByReservoirAndId(@Param("reservoir") Reservoir reservoir, @Param("id") Long id);

    @Query(value = "select distinct ps.product from ProductSize ps where ps.code in (:codes) ")
    List<Product> searchAllReservoir(@Param("codes") Set<Long> codes);

    boolean existsByCode(Long code);
}
