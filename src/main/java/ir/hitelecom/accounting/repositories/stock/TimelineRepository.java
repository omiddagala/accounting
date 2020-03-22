package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.entities.stock.Timeline;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimelineRepository extends CrudRepository<Timeline,Long> {

    List<Timeline> findByProductSize(ProductSize productSize);
    void deleteByProductSizeProduct(Product product);
}
