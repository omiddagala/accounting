package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.stock.Reservoir;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservoirRepository extends CrudRepository<Reservoir,Long> {

    @Query(value = "from Reservoir s where (:name = null or s.name like %:name%)")
    List<Reservoir> search(@Param("name") String name);

    boolean existsByName(String name);
}
