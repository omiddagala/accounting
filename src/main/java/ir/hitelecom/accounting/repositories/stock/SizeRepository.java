package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.stock.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SizeRepository extends CrudRepository<Size,Long> {

    @Query(value = "from Size s where (:svalue = null or s.value like %:svalue%)")
    List<Size> search(@Param("svalue") String value);
}
