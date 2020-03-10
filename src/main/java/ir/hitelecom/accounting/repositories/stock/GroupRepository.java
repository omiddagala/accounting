package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group,Long> {

    List<Group> findByUsername(String username);
}
